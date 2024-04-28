package com.backend.prueba.adapter.v1.price;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.prueba.model.service.ProductPrice;
import com.backend.prueba.services.ProductPriceService;

@RestController
public class PriceController {

    ProductPriceService priceService;

    PriceController() {
        priceService = new ProductPriceService();
    }

    @GetMapping("/")
    public String getHelloWorld() {
        return "Hello, World!";
    }


    @GetMapping("/api/v1/price/{brand_id}/{product_id}/{date}")
    @ResponseBody
    public ResponseEntity<Object> getPriceForPoductOnDate(
        @PathVariable(name="product_id", required=true) Long productId,
        @PathVariable(name="brand_id", required=true) Long brandId,
        @PathVariable(name="date", required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.NONE) String dateString) {
            // We will assume that we do not have time precision up to the millisecond level.
            LocalDateTime pathDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            Timestamp timestamp = Timestamp.valueOf(pathDate);


            if (productId < 0 || brandId <= 0 ) {
                ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                    HttpStatus.BAD_REQUEST,
                    "Unsigned product_id and brand_id must be have a positive value.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
            }
            Optional<ProductPrice> productPrice = this.priceService.getProductPrice(productId, brandId, timestamp);
            // TODO: We might need to include internal server error handling here.
            return productPrice.isPresent() ?
                ResponseEntity.ok(productPrice.get()) :
                ResponseEntity.notFound().build();
    }
}
