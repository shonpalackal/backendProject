package com.backend.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    Long id;
    String title;
    Double price;
    Category category;
    String description;
    String image;
}
