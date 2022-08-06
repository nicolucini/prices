package com.test.prices.core.domain.exception;

public class PriceNotFoundException extends Exception {
    public PriceNotFoundException() {
        super("There arent prices for selected parameters");
    }
}
