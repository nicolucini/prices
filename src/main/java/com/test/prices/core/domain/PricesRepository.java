package com.test.prices.core.domain;

import com.test.prices.core.infrastructure.PriceItem;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PricesRepository {
        Optional<PriceItem> findByDate(Long brandId, Long productId, Date date) throws Exception;
}
