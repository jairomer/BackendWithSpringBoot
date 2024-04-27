package com.backend.prueba.model.service.exceptions;

import java.math.BigDecimal;

public class PricePrecisionException extends Exception{
   public PricePrecisionException(String field, BigDecimal value) {
    super(String.format("Cannot have preicision larger than 2 in field '%s' for value '%s'", field, value.toString()));
   }
}
