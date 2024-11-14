package com.backend.ecommerce.services;

import com.backend.ecommerce.models.Product;

public interface ProductService {

    Product getProductByID(Long id);
}
