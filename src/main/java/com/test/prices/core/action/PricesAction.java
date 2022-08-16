package com.test.prices.core.action;

import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.Price;

public interface PricesAction {
    Price getPrice(GetPriceData priceData) throws Exception;
}
