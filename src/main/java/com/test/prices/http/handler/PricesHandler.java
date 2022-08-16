package com.test.prices.http.handler;

import com.test.prices.http.response.PriceResponse;
import org.springframework.http.ResponseEntity;

public interface PricesHandler {
    ResponseEntity<PriceResponse> price(Long brandId, Long productId, String date) throws Exception;
}
