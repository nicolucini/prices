package com.test.prices.http.response;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming()
public class PriceResponse {
    private int brandId;
    private int productId;
    private long priceList;
    private BigDecimal price;

    public PriceResponse(int brandId, int productId, long priceList, BigDecimal price) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
        this.price = price;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getProductId() {
        return productId;
    }

    public long getPriceList() {
        return priceList;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
