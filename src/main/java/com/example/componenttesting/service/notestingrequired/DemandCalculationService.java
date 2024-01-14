package com.example.componenttesting.service.notestingrequired;

import com.example.componenttesting.domain.Demand;
import com.example.componenttesting.domain.Offer;
import com.example.componenttesting.persistence.DemandRepository;
import org.springframework.stereotype.Service;

@Service("untestedDemandCalculationService")
public class DemandCalculationService {

    private final DemandRepository demandRepository;

    public DemandCalculationService(DemandRepository demandRepository) {
        this.demandRepository = demandRepository;
    }

    public Demand recalculateWith(Offer offer) {
        Demand previousDemand = demandRepository.findByProduct(offer.product()).orElseThrow();

        return DemandCalculator.of(previousDemand).recalculateWith(offer);
    }
}
