package com.example.componenttesting.service.testingrequired;

import com.example.componenttesting.domain.Demand;
import com.example.componenttesting.domain.Product;
import com.example.componenttesting.persistence.DemandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.componenttesting.TestDataUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DemandCalculationServiceMockTest {

    @Mock
    private DemandRepository demandRepository;

    @InjectMocks
    private DemandCalculationService demandCalculationService;

    private final Product chosenProduct = testProduct();

    private final Demand previousDemand = testDemand(chosenProduct);

    @BeforeEach
    void setup() {
        when(demandRepository.findByProduct(any())).thenReturn(Optional.of(previousDemand));
    }

    @Test
    void given_not_accepted_offer__return_previous_demand() {
        // given
        var offer = testOffer(1.0, false, chosenProduct);

        // when
        Demand newDemand = demandCalculationService.recalculateWith(offer);

        // then
        assertThat(newDemand).isEqualTo(previousDemand);
    }

    @Test
    void given_too_high_amount_accepted__throw_runtime_exception() {
        // given
        var offer = testOffer(100.0, true, chosenProduct);

        // when + then
        assertThatThrownBy(() -> demandCalculationService.recalculateWith(offer)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void given_correct_amount_in_offer__recalculate_the_demand() {
        // given
        var offer = testOffer(7.0, true, chosenProduct);

        // when
        Demand newDemand = demandCalculationService.recalculateWith(offer);

        // then
        assertThat(newDemand).hasNoNullFieldsOrProperties();
        assertThat(newDemand.amount()).isEqualTo(23.0);
        assertThat(newDemand.id()).isEqualTo(previousDemand.id());
        assertThat(newDemand.product()).isEqualTo(previousDemand.product());
    }
}