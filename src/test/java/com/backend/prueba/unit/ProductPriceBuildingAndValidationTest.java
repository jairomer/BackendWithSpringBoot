package com.backend.prueba.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.backend.prueba.model.service.ProductPrice;
import com.backend.prueba.model.service.exceptions.MissingFieldsException;
import com.backend.prueba.model.service.exceptions.InvalidValueException;

public class ProductPriceBuildingAndValidationTest {

    @Test()
    public void TestProductPriceAssemblyHappyPath() {
        assertDoesNotThrow(() -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setBrandId((long)1);
            builder = builder.setProductId(1);
            builder = builder.setPriceListId(1);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(LocalDate.MIN);
            builder = builder.setEndDate(LocalDate.MAX);

            ProductPrice productPrice = builder.build();
            assertEquals(1, productPrice.getBrandId());
            assertEquals(1, productPrice.getProductId());
            assertEquals(1, productPrice.getPriceListId());
            assertEquals(0.0, productPrice.getFinalPrice().doubleValue());
            assertEquals(LocalDate.MIN, productPrice.getStartDate());
            assertEquals(LocalDate.MAX, productPrice.getEndDate());
        });
    }
    
    @Test()
    public void TestProductPriceAssemblyMissingFieldBrandId() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1);
            builder = builder.setPriceListId(1);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(LocalDate.MIN);
            builder = builder.setEndDate(LocalDate.MAX);

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    public void TestProductPriceAssemblyMissingFieldProductId() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setBrandId((long)1);
            builder = builder.setPriceListId(1);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(LocalDate.MIN);
            builder = builder.setEndDate(LocalDate.MAX);

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    public void TestProductPriceAssemblyMissingFieldPriceListId() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1);
            builder = builder.setBrandId((long)1);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(LocalDate.MIN);
            builder = builder.setEndDate(LocalDate.MAX);

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    public void TestProductPriceAssemblyMissingFieldFinalPriceId() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1);
            builder = builder.setBrandId((long)1);
            builder = builder.setPriceListId(1);
            builder = builder.setStartDate(LocalDate.MIN);
            builder = builder.setEndDate(LocalDate.MAX);

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    public void TestProductPriceAssemblyMissingFieldStartDate() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1);
            builder = builder.setBrandId((long)1);
            builder = builder.setPriceListId(1);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setEndDate(LocalDate.MAX);

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }
    
    @Test()
    public void TestProductPriceAssemblyMissingFieldEndDate() {
        MissingFieldsException exception = assertThrows(
            MissingFieldsException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setProductId(1);
            builder = builder.setBrandId((long)1);
            builder = builder.setPriceListId(1);
            builder = builder.setFinalPrice(0.0);
            builder = builder.setStartDate(LocalDate.MAX);

            builder.build();
        });

        assertThat(exception).isInstanceOf(MissingFieldsException.class);
    }

    @Test()
    public void TestProductPriceAssemblyNegativeValueFieldBrandId() {
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
    public void TestProductPriceAssemblyNegativeValueFieldProductId() {
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
    public void TestProductPriceAssemblyNegativeValueFieldPriceListId() {
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
    public void TestProductPriceAssemblyStartDateAfterEndDate() {
        InvalidValueException exception = assertThrows(
            InvalidValueException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setEndDate(LocalDate.MIN);
            builder = builder.setStartDate(LocalDate.MAX);
        });

        assertThat(exception)
            .isInstanceOf(InvalidValueException.class)
            .hasMessageContaining("cannot come after the end date");
    } 

    @Test()
    public void TestProductPriceAssemblyEndDateBeforeStartDate() {
        InvalidValueException exception = assertThrows(
            InvalidValueException.class,
            () -> {
            ProductPrice.Builder builder = new ProductPrice.Builder();
            builder = builder.setStartDate(LocalDate.MAX);
            builder = builder.setEndDate(LocalDate.MIN);
        });

        assertThat(exception)
            .isInstanceOf(InvalidValueException.class)
            .hasMessageContaining("cannot come after the end date");
    } 
}
