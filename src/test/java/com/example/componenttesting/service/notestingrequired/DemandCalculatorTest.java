package com.example.componenttesting.service.notestingrequired;

import com.example.componenttesting.domain.Demand;
import com.example.componenttesting.domain.Product;
import org.junit.jupiter.api.Test;

import static com.example.componenttesting.TestDataUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DemandCalculatorTest {

    private final Product chosenProduct = testProduct();

    private final Demand previousDemand = testDemand(chosenProduct);


    @Test
    void given_not_accepted_offer__return_previous_demand() {
        // given
        var offer = testOffer(1.0, false, chosenProduct);

        // when
        Demand newDemand = DemandCalculator.of(previousDemand).recalculateWith(offer);

        // then
        assertThat(newDemand).isEqualTo(previousDemand);
    }

    @Test
    void given_too_high_amount_accepted__throw_runtime_exception() {
        // given
        var offer = testOffer(100.0, true, chosenProduct);

        // when + then
        assertThatThrownBy(() -> DemandCalculator.of(previousDemand).recalculateWith(offer))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void given_correct_amount_in_offer__recalculate_the_demand() {
        // given
        var offer = testOffer(7.0, true, chosenProduct);

        // when
        Demand newDemand = DemandCalculator.of(previousDemand).recalculateWith(offer);

        // then
        assertThat(newDemand).hasNoNullFieldsOrProperties();
        assertThat(newDemand.amount()).isEqualTo(23.0);
        assertThat(newDemand.id()).isEqualTo(previousDemand.id());
        assertThat(newDemand.product()).isEqualTo(previousDemand.product());
    }

}