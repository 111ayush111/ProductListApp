package com.bitsandbytes.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


//    one category can have many product , so it is mapped by category
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products= new ArrayList<>();
}
