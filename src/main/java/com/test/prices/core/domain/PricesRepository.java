package com.test.prices.core.domain;

public interface PricesRepository {
    Price getPrice(GetPriceData priceData);
}
