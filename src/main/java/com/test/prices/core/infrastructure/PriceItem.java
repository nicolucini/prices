package com.test.prices.core.infrastructure;

import com.test.prices.core.domain.Price;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "prices")
public class PriceItem {
    @Column(name = "brand_id")
    private int brandId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Id
    @Column(name = "price_list")
    private long priceList;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "priority")
    private int priority;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "currency")
    private String currency;

    public PriceItem() {
    }

    public PriceItem(int brandId, Date startDate, Date endDate, int priceList, int productId, int priority, BigDecimal price, String currency) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public int getBrandId() {
        return brandId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public long getPriceList() {
        return priceList;
    }

    public int getProductId() {
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

    public Price toPrice() {
        return new Price(this.brandId, this.productId, this.priceList, this.price);
    }
}
