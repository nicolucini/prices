package com.test.prices.core.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Price {
    private Long brandId;
    private Date startDate;
    private Date endDate;
    private Long priceList;
    private Long productId;
    private int priority;
    private BigDecimal price;
    private String currency;

    public Price(Long brandId, Date startDate, Date endDate, Long priceList, Long productId, int priority, BigDecimal price, String currency) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Long getPriceList() {
        return priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public int getPriority() {
        return priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

}
