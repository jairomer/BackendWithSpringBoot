package com.backend.prueba.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import com.backend.prueba.model.db.Price;
import com.backend.prueba.model.service.ProductPrice;
import com.backend.prueba.model.service.exceptions.MissingFieldsException;
import com.backend.prueba.model.service.exceptions.PriceScaleException;
import com.backend.prueba.model.service.exceptions.InvalidValueException;

class ProductPriceBuildingAndValidationTest {

    @Test()
    void TestProductPriceAssemblyHappyPath() {
        assertDoesNotThrow(() -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setBrandId(1L);
            builder = builder.setProductId(1L);
            builder = builder.setPriceListId(1L);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(Timestamp.valueOf("2020-06-14 15:00:00"));
            builder = builder.setEndDate(Timestamp.valueOf("2020-12-14 15:00:00"));

            ProductPrice productPrice = builder.build();
            assertEquals(1, productPrice.getBrandId());
            assertEquals(1, productPrice.getProductId());
            assertEquals(1, productPrice.getPriceListId());
            assertEquals(0.0, productPrice.getFinalPrice().doubleValue());
            assertEquals(Timestamp.valueOf("2020-06-14 15:00:00"), productPrice.getStartDate());
            assertEquals(Timestamp.valueOf("2020-12-14 15:00:00"), productPrice.getEndDate());
        });
    }
    
    @Test()
    void TestProductPriceAssemblyTestExcesivePriceScale() {
        PriceScaleException exception = assertThrows(
            PriceScaleException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setFinalPrice(1.001);
        });

        assertThat(exception).isInstanceOf(PriceScaleException.class);
    }
    
    @Test()
    void TestProductPriceAssemblyTestNegativePrice() {
        InvalidValueException exception = assertThrows(
            InvalidValueException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setFinalPrice(-1.00);
        });

        assertThat(exception).isInstanceOf(InvalidValueException.class);
    }
    
    @Test()
    void TestProductPriceAssemblyMissingFieldBrandId() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1L);
            builder = builder.setPriceListId(1L);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(Timestamp.valueOf("2020-06-14 15:00:00"));
            builder = builder.setEndDate(Timestamp.valueOf("2020-12-14 15:00:00"));

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    void TestProductPriceAssemblyMissingFieldProductId() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setBrandId(1L);
            builder = builder.setPriceListId(1L);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(Timestamp.valueOf("2020-06-14 15:00:00"));
            builder = builder.setEndDate(Timestamp.valueOf("2020-12-14 15:00:00"));

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    void TestProductPriceAssemblyMissingFieldPriceListId() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1L);
            builder = builder.setBrandId(1L);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(Timestamp.valueOf("2020-06-14 15:00:00"));
            builder = builder.setEndDate(Timestamp.valueOf("2020-12-14 15:00:00"));

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    void TestProductPriceAssemblyMissingFieldFinalPriceId() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1L);
            builder = builder.setBrandId(1L);
            builder = builder.setPriceListId(1L);
            builder = builder.setStartDate(Timestamp.valueOf("2020-06-14 15:00:00"));
            builder = builder.setEndDate(Timestamp.valueOf("2020-12-14 15:00:00"));

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    void TestProductPriceAssemblyMissingFieldStartDate() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1L);
            builder = builder.setBrandId(1L);
            builder = builder.setPriceListId(1L);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setEndDate(Timestamp.valueOf("2020-12-14 15:00:00"));

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    void TestProductPriceAssemblyMissingFieldEndDate() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1L);
            builder = builder.setBrandId(1L);
            builder = builder.setPriceListId(1L);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(Timestamp.valueOf("2020-12-14 15:00:00"));

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }

    @Test()
    void TestProductPriceAssemblyNegativeValueFieldBrandId() {
        InvalidValueException exception = assertThrows(
            InvalidValueException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setBrandId((long)-1);
        });

        assertThat(exception)
            .isInstanceOf(InvalidValueException.class)
            .hasMessageContaining("Invalid value");
    }
    
    @Test()
    void TestProductPriceAssemblyNegativeValueFieldProductId() {
        InvalidValueException exception = assertThrows(
            InvalidValueException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId((long)-1);
        });

        assertThat(exception)
            .isInstanceOf(InvalidValueException.class)
            .hasMessageContaining("Invalid value");
    }

    
    @Test()
    void TestProductPriceAssemblyNegativeValueFieldPriceListId() {
        InvalidValueException exception = assertThrows(
            InvalidValueException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setPriceListId((long)-1);
        });

        assertThat(exception)
            .isInstanceOf(InvalidValueException.class)
            .hasMessageContaining("Invalid value");
    }
    
    @Test()
    void TestProductPriceAssemblyStartDateAfterEndDate() {
        InvalidValueException exception = assertThrows(
            InvalidValueException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setEndDate(Timestamp.valueOf("2020-06-14 15:00:00"));
            builder = builder.setStartDate(Timestamp.valueOf("2020-12-14 15:00:00"));
        });

        assertThat(exception)
            .isInstanceOf(InvalidValueException.class)
            .hasMessageContaining("cannot come after the end date");
    } 

    @Test()
    void TestProductPriceAssemblyEndDateBeforeStartDate() {
        InvalidValueException exception = assertThrows(
            InvalidValueException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setStartDate(Timestamp.valueOf("2020-12-14 15:00:00"));
            builder = builder.setEndDate(Timestamp.valueOf("2020-06-14 15:00:00"));
        });

        assertThat(exception)
            .isInstanceOf(InvalidValueException.class)
            .hasMessageContaining("cannot come after the end date");
    } 
}
