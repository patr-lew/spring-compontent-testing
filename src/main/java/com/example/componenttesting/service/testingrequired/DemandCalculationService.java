package com.example.componenttesting.service.testingrequired;

import com.example.componenttesting.domain.Demand;
import com.example.componenttesting.domain.Offer;
import com.example.componenttesting.persistence.DemandRepository;
import org.springframework.stereotype.Service;

@Service("testedDemandCalculationService")
public class DemandCalculationService {

    private final DemandRepository demandRepository;

    public DemandCalculationService(DemandRepository demandRepository) {
        this.demandRepository = demandRepository;
    }


    public Demand recalculateWith(Offer offer) {
        Demand previousDemand = demandRepository.findByProduct(offer.product()).orElseThrow();

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