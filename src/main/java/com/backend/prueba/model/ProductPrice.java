package com.backend.prueba.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Container class for the price of a product between two dates.
 */
public class ProductPrice implements Serializable{
    private int productId;
    private int brandId;
    private int priceListId;
    private java.util.Date startDate;
    private java.util.Date endDate;
    private BigDecimal finalPrice;
    private int version = 1;
    
    public ProductPrice(int productId, int brandId, int priceListId, Date startDate, Date endDate,
            BigDecimal finalPrice) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceListId = priceListId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.finalPrice = finalPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(int priceListId) {
        this.priceListId = priceListId;
    }

    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }
    
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
