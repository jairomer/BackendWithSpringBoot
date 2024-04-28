package com.backend.prueba.model.db;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Products")
public class Product {
    @Id
    @Column(name = "ID")
    private Long id;
}