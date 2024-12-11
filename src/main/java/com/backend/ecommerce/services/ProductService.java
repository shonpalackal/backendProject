package com.backend.ecommerce.services;

import com.backend.ecommerce.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductByID(Long id);
    List<Product> getAllProducts();
    Product replaceProductByID(Long id, Product product);
}
