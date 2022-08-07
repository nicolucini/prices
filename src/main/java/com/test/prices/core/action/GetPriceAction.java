package com.test.prices.core.action;

import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.PricesRepository;
import com.test.prices.core.domain.exception.PriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPriceAction {

    @Autowired
    private final PricesRepository pricesRepository;

    public GetPriceAction(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    public Price getPrice(GetPriceData priceData) throws Exception {
        return pricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())
                .orElseThrow(PriceNotFoundException::new)
                .toPriceResponseData();
    }
}
