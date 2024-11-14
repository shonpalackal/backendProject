package com.backend.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {
    Long id;
    String title;
    Double price;
    String category;
    String description;
    String image;
}
