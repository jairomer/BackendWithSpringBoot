package com.backend.prueba.model.service.exceptions;

import java.time.LocalDate;

public class InvalidValueException extends Exception {
    public InvalidValueException(String field, String value) {
        super(String.format("Invalid value '%s' set for field '%s'", value, field));
    }
    public InvalidValueException(String field, Long value) {
        super(String.format("Invalid value '%s' set for field '%s'", value.toString(), field));
    }
    public InvalidValueException(LocalDate startDate, LocalDate endDate) {
        super(String.format("Start date '%s' cannot come after the end date '%s'", startDate.toString(), endDate.toString()));
    }
}
