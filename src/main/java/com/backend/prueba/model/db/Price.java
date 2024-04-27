package com.backend.prueba.model.db;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;


@Entity
@Table(name = "PRICES")
public class Price {

    @Id
    @Column(name = "PRICE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private Product product;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private java.sql.Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private java.sql.Date endDate;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID")
    private Brand brand;

    @Column(name = "PRIORITY")
    private int priority;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Column(name = "FINAL_PRICE", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "CURRENCY")
    private String currency;
}
