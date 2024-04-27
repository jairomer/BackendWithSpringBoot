package com.backend.prueba.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.NonNull;

import com.backend.prueba.model.service.ProductPrice;

public class ProductPriceService {
    private static final Logger logger = LoggerFactory.getLogger(ProductPriceService.class);

    /**
     * Given a product and brand ids, get the ProductPrice on a certain date.
     * @param productId
     * @param brandId
     * @param date
     * @return Optional<ProductPrice>
     */
    public Optional<ProductPrice> getProductPrice(@NonNull Long productId, @NonNull Long brandId, @NonNull LocalDate date) {
        ProductPrice.Builder builder = new ProductPrice.Builder();
        ProductPrice productPrice = null;
        try {
            builder = builder.setProductId(productId);
            builder = builder.setBrandId(brandId);
            builder = builder.setPriceListId(brandId);

            // TODO: Remove this dummy data.
            builder = builder.setStartDate(LocalDate.MIN);
            builder = builder.setEndDate(LocalDate.MAX);
            builder = builder.setFinalPrice(new BigDecimal(0));
            productPrice = builder.build();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return Optional.ofNullable(productPrice);
    }
    
}
