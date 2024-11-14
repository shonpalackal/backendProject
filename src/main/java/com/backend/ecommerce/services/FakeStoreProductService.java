package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.FakeStoreDTO;
import com.backend.ecommerce.models.Category;
import com.backend.ecommerce.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDTOtoProduct(FakeStoreDTO fakeStoreDTO) {
        Product product = new Product();
        product.setId(fakeStoreDTO.getId());
        product.setTitle(fakeStoreDTO.getTitle());
        product.setPrice(fakeStoreDTO.getPrice());
        product.setDescription(fakeStoreDTO.getDescription());
        Category category = new Category();
        category.setName(fakeStoreDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductByID(Long id) {

        FakeStoreDTO fakeStoreDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreDTO.class);

        if (fakeStoreDTO == null) {
            return null;
        }

        return convertFakeStoreDTOtoProduct(fakeStoreDTO);
    }
}
