package com.test.prices.core.domain.exception;

public class PriceNotFoundException extends BusinessException {
    public PriceNotFoundException() {
        super("There arent prices for selected parameters");
    }
}
