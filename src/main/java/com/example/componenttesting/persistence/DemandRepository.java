package com.example.componenttesting.persistence;

import com.example.componenttesting.domain.Demand;
import com.example.componenttesting.domain.Product;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DemandRepository {

    private static final Set<Demand> DEMANDS = Collections.synchronizedSet(new HashSet<>());

    public Optional<Demand> findByProduct(Product product) {
        return DEMANDS.stream()
                .filter(d -> d.product().id().equals(product.id()))
                .findAny();
    }

    public void add(Demand demand) {
        DEMANDS.add(demand);
    }

    public void clear() {
        DEMANDS.clear();
    }
}
