package com.test.prices.core.domain;

import com.test.prices.core.infrastructure.PriceItem;

import java.util.Date;
import java.util.List;

public interface PricesRepository {
    List<PriceItem> findByDate(int brandId, int productId, Date date);
}
