package com.bitsandbytes.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//entity is connected to database
@Entity
@Getter @Setter
public class Product {

//    data structure(product details) is the attributes of entity (product)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;

//category_id is in product as foreign key as id of category
//many product can have same category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)// join by name category id
    private Category category;



}
