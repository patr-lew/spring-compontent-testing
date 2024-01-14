package com.example.componenttesting.service.notestingrequired;

import com.example.componenttesting.domain.Demand;
import com.example.componenttesting.domain.Offer;

public class DemandCalculator {

    private final Demand previousDemand;

    private DemandCalculator(Demand previousDemand) {
        this.previousDemand = previousDemand;
    }

    public static DemandCalculator of(Demand demand) {
        return new DemandCalculator(demand);
    }

    public Demand recalculateWith(Offer offer) {
        if (!offer.isAccepted()) {
            return previousDemand;
        }

        if (offer.amount() > previousDemand.amount()) {
            throw new RuntimeException("Offer is greater than demand");
        }

        var newDemand = previousDemand.amount() - offer.amount();
        return previousDemand.withAmount(newDemand);
    }
}
