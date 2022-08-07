package com.test.prices.core.domain;

import com.test.prices.core.domain.exception.PriceNotFoundException;
import com.test.prices.core.infrastructure.PriceItem;

import java.util.Date;
import java.util.List;

public interface PricesRepository {
    GetPriceResponseData findByDate(Long brandId, Long productId, Date date) throws PriceNotFoundException;
}
