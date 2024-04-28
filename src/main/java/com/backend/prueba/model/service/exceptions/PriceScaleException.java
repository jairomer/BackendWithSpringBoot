package com.backend.prueba.model.service.exceptions;

import java.math.BigDecimal;

public class PriceScaleException extends Exception{
   public PriceScaleException(String field, BigDecimal value) {
    super(String.format("Cannot have precision larger than 2 in field '%s' for value '%s'", field, value.toString()));
   }
}
