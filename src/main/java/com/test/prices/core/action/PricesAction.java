package com.test.prices.core.action;

import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.PriceActionData;

import java.math.BigDecimal;

public class PricesAction {
    public Price getPrice(PriceActionData priceData) {
        return new Price(1, 1, 1, BigDecimal.ONE);
    }
}
