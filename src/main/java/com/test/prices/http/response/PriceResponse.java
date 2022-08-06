package com.test.prices.http.response;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming()
public class PriceResponse {
    private Long brandId;
    private Long productId;
    private Long priceList;
    private BigDecimal price;

    public PriceResponse(Long brandId, Long productId, long priceList, BigDecimal price) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getPriceList() {
        return priceList;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
