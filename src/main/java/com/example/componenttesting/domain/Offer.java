package com.example.componenttesting.domain;

public record Offer(Long id, Product product, Double amount, Double price, boolean isAccepted) {
}
