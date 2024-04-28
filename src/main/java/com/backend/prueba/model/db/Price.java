package com.backend.prueba.model.db;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Table(name = "PRICES")
@Entity
public class Price {
    @Id
    @Column(name = "PRICE_LIST_ID")
    @Getter @Setter @NonNull private Long id;
   
    @Column(
        name = "PRIORITY",
        nullable=false,
        columnDefinition = "int default 0")
    @Getter @Setter private int priority;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    @Getter @Setter @NonNull private Product product;
    
    @ManyToOne
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID")
    @Getter @Setter @NonNull private Brand brand;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter @NonNull private java.sql.Timestamp startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter @NonNull private java.sql.Timestamp endDate;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Column(name = "FINAL_PRICE", nullable = false)
    @Getter @Setter @NonNull private BigDecimal price;

    @Column(name = "CURRENCY")
    @Getter @Setter @NonNull private String currency;
}
