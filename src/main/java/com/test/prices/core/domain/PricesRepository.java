package com.test.prices.core.domain;

import com.test.prices.core.domain.exception.PriceNotFoundException;
import com.test.prices.core.infrastructure.PriceItem;

import java.util.Date;
import java.util.Optional;

public interface PricesRepository {
        Optional<PriceItem> findByDate(Long brandId, Long productId, Date date) throws Exception;
}
