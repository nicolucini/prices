package com.test.prices.core.domain;

import java.math.BigDecimal;

public class GetPriceResponseData {
    private Long brandId;
    private Long productId;
    private Long priceList;
    private BigDecimal price;

    public GetPriceResponseData(Long brandId, Long productId, long priceList, BigDecimal price) {
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
