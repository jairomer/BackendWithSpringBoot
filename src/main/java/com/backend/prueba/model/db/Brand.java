package com.backend.prueba.model.db;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;;

@Entity
@Table(name = "BRANDS")
public class Brand {
    @Id
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "BRAND_NAME")
    private String name;
}
