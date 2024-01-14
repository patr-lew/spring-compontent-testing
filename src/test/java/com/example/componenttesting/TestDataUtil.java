package com.example.componenttesting;

import com.example.componenttesting.domain.Demand;
import com.example.componenttesting.domain.Offer;
import com.example.componenttesting.domain.Product;

public class TestDataUtil {

    private TestDataUtil() {
    }

    public static Demand testDemand(Product product) {
        return new Demand(1L, 30.0, product);
    }

    public static Product testProduct() {
        return new Product(1L, "Apples");
    }

    public static Offer testOffer(double amount, boolean accepted, Product product) {
        return new Offer(1L, product, amount, 9.99, accepted);
    }
}
