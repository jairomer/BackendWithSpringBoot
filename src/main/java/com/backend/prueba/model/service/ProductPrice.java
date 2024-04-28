package com.backend.prueba.model.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.backend.prueba.model.service.exceptions.PricePrecisionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.prueba.model.service.exceptions.InvalidValueException;
import com.backend.prueba.model.service.exceptions.MissingFieldsException;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

/**
 * Container class for the price of a product between two dates.
 */
public class ProductPrice implements Serializable{

    @Setter @Getter @NonNull private Long productId;
    @Setter @Getter @NonNull private Long brandId;
    @Setter @Getter @NonNull private Long priceListId;
    @Setter @Getter @NonNull private Timestamp startDate;
    @Setter @Getter @NonNull private Timestamp endDate;
    @Setter @Getter @NonNull private BigDecimal finalPrice;

    public ProductPrice() {
        this.productId = 0L;
        this.brandId = 0L;
        this.priceListId = 0L;
        this.startDate = new Timestamp(0);
        this.endDate = new Timestamp(Long.MAX_VALUE);
        this.finalPrice = new BigDecimal(0.0);
    }

    public ProductPrice(@NonNull Long productId, @NonNull Long brandId, @NonNull Long priceListId,
            @NonNull Timestamp startDate, @NonNull Timestamp endDate, @NonNull BigDecimal finalPrice) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceListId = priceListId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.finalPrice = finalPrice;
    }

    /**
     * Creates validated ProductPrice instances.
     * 
     * A ProductPrice must verify the following postconditions:
     *  - Start date is before end date.
     *  - Final price has precision of 2, not more nor less.
     *  - All fields need to have a defined value. 
     * 
     */
    static public class Builder {
        @Getter @NonNull private Long productId;
        @Getter @NonNull private Long brandId;
        @Getter @NonNull private Long priceListId;
        @Getter @NonNull private Timestamp startDate;
        @Getter @NonNull private Timestamp endDate;
        @Getter @NonNull private BigDecimal finalPrice;

        public Builder() { }

        public Builder setProductId(Long productId) throws InvalidValueException {
            if (productId > 0) {
                this.productId = productId;
                return this;
            }
            throw new InvalidValueException("productId", productId);
        }
        
        public Builder setBrandId(Long brandId) throws InvalidValueException {
            if (brandId > 0) {
                this.brandId = brandId;
                return this;
            }
            throw new InvalidValueException("brandId", brandId);
        }

        public Builder setPriceListId(Long priceListId) throws InvalidValueException {
            if (priceListId > 0) {
                this.priceListId = priceListId;
                return this;
            }
            throw new InvalidValueException("priceListId", priceListId);
        }
        
        public Builder setStartDate(Timestamp startDate) throws InvalidValueException {
            if (this.endDate != null && this.endDate.before(startDate)) {
                throw new InvalidValueException(startDate, this.endDate);
            }
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Timestamp endDate) throws InvalidValueException {
            if (this.startDate != null && this.startDate.after(endDate)) {
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

        public static ProductPrice buildFromJson(String json) throws PricePrecisionException, InvalidValueException, JsonMappingException, JsonProcessingException, MissingFieldsException  {

            ObjectMapper mapper = new ObjectMapper();
            ProductPrice readObj = mapper.readValue(json, ProductPrice.class);

            Builder builder = new Builder();
            builder = builder.setBrandId(readObj.getBrandId());
            builder = builder.setProductId(readObj.getProductId());
            builder = builder.setPriceListId(readObj.getPriceListId());
            builder = builder.setFinalPrice(readObj.getFinalPrice());
            builder = builder.setStartDate(readObj.getStartDate());
            builder = builder.setEndDate(readObj.getEndDate());

            return builder.build();
        }
    }

}
