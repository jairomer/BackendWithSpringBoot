package com.backend.prueba.model.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.backend.prueba.model.service.exceptions.PricePrecisionException;
import com.backend.prueba.model.service.exceptions.InvalidValueException;
import com.backend.prueba.model.service.exceptions.MissingFieldsException;

import lombok.Getter;
import lombok.NonNull;

/**
 * Container class for the price of a product between two dates.
 */
public class ProductPrice implements Serializable{

    @Getter @NonNull private final Long productId;
    @Getter @NonNull private final Long brandId;
    @Getter @NonNull private final Long priceListId;
    @Getter @NonNull private final LocalDate startDate;
    @Getter @NonNull private final LocalDate endDate;
    @Getter @NonNull private final BigDecimal finalPrice;

    private ProductPrice(@NonNull Long productId, @NonNull Long brandId, @NonNull Long priceListId,
            @NonNull LocalDate startDate, @NonNull LocalDate endDate, @NonNull BigDecimal finalPrice) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceListId = priceListId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.finalPrice = finalPrice;
    }

    /**
     * Creates validated ProductPrice instances.
     */
    static public class Builder {
        @Getter @NonNull private Long productId;
        @Getter @NonNull private Long brandId;
        @Getter @NonNull private Long priceListId;
        @Getter @NonNull private LocalDate startDate;
        @Getter @NonNull private LocalDate endDate;
        @Getter @NonNull private BigDecimal finalPrice;

        public Builder() { }

        public Builder setProductId(Long productId) throws InvalidValueException {
            if (productId > 0) {
                this.productId = productId;
                return this;
            }
            throw new InvalidValueException("productId", productId);
        }
        
        public Builder setProductId(int productId) throws InvalidValueException {
            return this.setProductId((long) productId);
        }
        
        public Builder setBrandId(Long brandId) throws InvalidValueException {
            if (brandId > 0) {
                this.brandId = brandId;
                return this;
            }
            throw new InvalidValueException("brandId", brandId);
        }
        
        public Builder setBrandId(int brandId) throws InvalidValueException {
            return this.setBrandId((long) brandId);
        }

        public Builder setPriceListId(Long priceListId) throws InvalidValueException {
            if (priceListId > 0) {
                this.priceListId = priceListId;
                return this;
            }
            throw new InvalidValueException("priceListId", priceListId);
        }
        
        public Builder setPriceListId(int priceListId) throws InvalidValueException {
            return this.setPriceListId((long) priceListId);
        }
        
        public Builder setStartDate(LocalDate startDate) throws InvalidValueException {
            if (this.endDate != null && this.endDate.isBefore(startDate)) {
                throw new InvalidValueException(startDate, this.endDate);
            }
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) throws InvalidValueException {
            if (this.startDate != null && this.startDate.isAfter(endDate)) {
                throw new InvalidValueException(this.startDate, endDate);
            }
            this.endDate = endDate;
            return this;
        }


        public Builder setFinalPrice(BigDecimal price) throws PricePrecisionException, InvalidValueException {
            if (price.precision() > 2) {
                throw new PricePrecisionException("finalPrice", price);
            }
            if (price.longValue() < 0) {
                throw new InvalidValueException("finalPrice", price.toString());
            }
            this.finalPrice = price;
            return this;
        }
        
        public Builder setFinalPrice(double price) throws PricePrecisionException, InvalidValueException {
            return setFinalPrice(new BigDecimal(price));
        }

        public ProductPrice build() throws MissingFieldsException {
            try {
                return new ProductPrice(productId, brandId, priceListId, startDate, endDate, finalPrice);
            } catch (Exception e)  {
                throw new MissingFieldsException(e);
            }
        }
    }

}
