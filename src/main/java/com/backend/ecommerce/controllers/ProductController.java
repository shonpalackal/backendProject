package com.backend.ecommerce.controllers;

import com.backend.ecommerce.models.Product;
import com.backend.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("{id}")
    Product getProductByID(@PathVariable("id") Long id) {
        return productService.getProductByID(id);
    }
}
