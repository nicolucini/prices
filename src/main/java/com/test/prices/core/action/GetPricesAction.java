package com.test.prices.core.action;

import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.PricesRepository;

public class GetPricesAction {

    private final PricesRepository pricesRepository;

    public GetPricesAction(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    public Price getPrice(GetPriceData priceData) {
        return pricesRepository.getPrice(priceData);
    }
}
