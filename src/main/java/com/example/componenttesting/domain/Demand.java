package com.example.componenttesting.domain;

public record Demand(Long id, Double amount, Product product) {

    public Demand withAmount(Double amount) {
        return new Demand(this.id, amount, this.product);
    }
}
