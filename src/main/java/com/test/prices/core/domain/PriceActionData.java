package com.test.prices.core.domain;

import java.util.Date;
import java.util.Objects;

public class PriceActionData {
    private int brandId;
    private int productId;
    private Date date;

    public PriceActionData(int brandId, int productId, Date date) {
        this.brandId = brandId;
        this.productId = productId;
        this.date = date;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getProductId() {
        return productId;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceActionData that = (PriceActionData) o;
        return brandId == that.brandId && productId == that.productId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, productId, date);
    }
}
