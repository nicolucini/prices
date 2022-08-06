package com.test.prices.core.domain;

import java.math.BigDecimal;

public class Price {
    private int brandId;
    private int productId;
    private long priceList;
    private BigDecimal price;

    public Price(int brandId, int productId, long priceList, BigDecimal price) {
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
