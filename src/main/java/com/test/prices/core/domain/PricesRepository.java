package com.test.prices.core.domain;

import com.test.prices.core.infrastructure.PriceItem;

public interface PricesRepository {
    Price getPrice(GetPriceData priceData) throws Throwable;
}
