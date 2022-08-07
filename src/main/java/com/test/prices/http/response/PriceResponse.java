package com.test.prices.http.response;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.util.Date;

@JsonNaming()
public class PriceResponse {
    private Long brandId;
    private Long productId;
    private Long priceList;
    private Date startDate;
    private Date endDate;
    private BigDecimal price;

    public PriceResponse(Long brandId, Long productId, Long priceList, Date startDate, Date endDate, BigDecimal price) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
