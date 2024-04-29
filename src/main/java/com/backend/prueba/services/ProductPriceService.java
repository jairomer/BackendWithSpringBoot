package com.backend.prueba.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.backend.prueba.model.db.Price;
import com.backend.prueba.model.service.ProductPrice;
import com.backend.prueba.model.service.exceptions.InvalidValueException;
import com.backend.prueba.model.service.exceptions.MissingFieldsException;
import com.backend.prueba.model.service.exceptions.PriceScaleException;
import com.backend.prueba.services.repositories.PriceRepositoryImpl;

import lombok.NonNull;

@Component
public class ProductPriceService {

    private final PriceRepositoryImpl priceRepo;
    private static final Logger logger = LoggerFactory.getLogger(ProductPriceService.class);

    public ProductPriceService(PriceRepositoryImpl priceRepository) {
        this.priceRepo = priceRepository;
    }

    /**
     * Given a product and brand ids, get the ProductPrice on a certain date.
     * @param productId
     * @param brandId
     * @param date
     * @return Optional<ProductPrice>
     * @throws InvalidValueException 
     * @throws PriceScaleException 
     * @throws MissingFieldsException 
     */
    public Optional<ProductPrice> getProductPrice(@NonNull Long productId, @NonNull Long brandId, @NonNull Timestamp date) throws InvalidValueException, PriceScaleException, MissingFieldsException {
        
        ProductPrice.Builder builder = new ProductPrice.Builder();

        builder = builder.setProductId(productId);
        builder = builder.setBrandId(brandId);
       
        Optional<Price> priceOfDate =
            priceRepo.getPriceForProductAndBrandInTimestamp(productId, brandId, date);
        
        if (priceOfDate.isEmpty()) {
            logger.info("Received empty results on price query.");
            return Optional.empty();
        }
        
        builder = builder.setPriceListId(priceOfDate.get().getId());
        builder = builder.setStartDate(priceOfDate.get().getStartDate());
        builder = builder.setEndDate(priceOfDate.get().getEndDate());
        builder = builder.setFinalPrice(priceOfDate.get().getFinalPrice());

        return Optional.ofNullable(builder.build());
    }
    
}
