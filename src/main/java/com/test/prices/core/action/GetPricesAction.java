package com.test.prices.core.action;

import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.PricesRepository;
import com.test.prices.core.domain.exception.PriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPricesAction {

    @Autowired
    private final PricesRepository pricesRepository;

    public GetPricesAction(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    public Price getPrice(GetPriceData priceData) throws Throwable {
        return pricesRepository.findByDate(priceData.getBrandId(),
                        priceData.getProductId(),
                        priceData.getDate()).stream().
                        findFirst().
                        orElseThrow(PriceNotFoundException::new)
                .toPrice();
    }
}
