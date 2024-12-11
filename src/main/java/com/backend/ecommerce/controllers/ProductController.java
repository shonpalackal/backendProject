package com.backend.ecommerce.controllers;

import com.backend.ecommerce.models.Product;
import com.backend.ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("{id}")
    ResponseEntity<Product> replaceProductByID(@PathVariable("id") Long id, @RequestBody Product product) {
        Product product1 = productService.replaceProductByID(id, product);
        if (product1 == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product1, HttpStatus.ALREADY_REPORTED);
    }
}
