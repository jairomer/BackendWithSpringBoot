package com.backend.prueba.controllers.v1.price;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.prueba.model.service.ProductPrice;
import com.backend.prueba.services.ProductPriceService;

@RestController
public class PriceController {

    private final ProductPriceService priceService;

    public PriceController(ProductPriceService priceService) {
        this.priceService = priceService;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

    @GetMapping("/api/v1/price/{brand_id}/{product_id}/{date}")
    public ResponseEntity<Object> getPriceForPoductOnDate(
        @PathVariable(name="product_id", required=true) Long productId,
        @PathVariable(name="brand_id", required=true) Long brandId,
        @PathVariable(name="date", required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.NONE) String dateString) {
            // We will assume that we do not have time precision up to the millisecond level.
            Timestamp timestamp = null;
            try {
                LocalDateTime pathDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                timestamp = Timestamp.valueOf(pathDate);
            } catch (Exception e) {
                    logger.debug("Unable to parse timestamp on path: %s", dateString);
            } finally {
                if (productId < 0 || brandId <= 0 || timestamp == null) {
                    // Uppon a path that is nott parseable to a meaningfull search, we will just return 404. 
                    String uri = String.format("/api/v1/price/%d/%d/%s", brandId, productId, dateString);
                    logger.debug("Client attempting to access non parseable URI: %s", uri);
                    return ResponseEntity.notFound().build();
                }
            }
            Optional<ProductPrice> productPrice = Optional.empty();
            try {
                productPrice = this.priceService.getProductPrice(productId, brandId, timestamp);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }

            return productPrice.isPresent() ?
                ResponseEntity.ok(productPrice.get()) :
                ResponseEntity.notFound().build();
    }
}
