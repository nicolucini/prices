package com.test.prices.core.domain;

import java.util.Date;
import java.util.Objects;

public class GetPriceData {
    private final Long brandId;
    private final Long productId;
    private final Date date;

    public GetPriceData(Long brandId, Long productId, Date date) {
        this.brandId = brandId;
        this.productId = productId;
        this.date = date;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPriceData that = (GetPriceData) o;
        return Objects.equals(brandId, that.brandId) && Objects.equals(productId, that.productId) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, productId, date);
    }
}
