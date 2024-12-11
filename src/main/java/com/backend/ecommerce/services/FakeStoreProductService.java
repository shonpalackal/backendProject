package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.FakeStoreDTO;
import com.backend.ecommerce.models.Category;
import com.backend.ecommerce.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    private FakeStoreDTO convertProductToFakeStoreDTO(Product product) {
        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
//        fakeStoreDTO.setId(product.getId());
        fakeStoreDTO.setTitle(product.getTitle());
        fakeStoreDTO.setPrice(product.getPrice());
        fakeStoreDTO.setDescription(product.getDescription());
        fakeStoreDTO.setCategory(product.getCategory().getName());
        return fakeStoreDTO;
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

    @Override
    public List<Product> getAllProducts() {

        // Due to Type Erasure, we need to use an array of FakeStoreDTO instead of a List
        FakeStoreDTO[] fakeStoreDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreDTO[].class);

        // Convert List<FakeStoreDTO> to List<Product>
        List<Product> returnList = new ArrayList<>();
        if (fakeStoreDTO != null) {
            for (FakeStoreDTO dto : fakeStoreDTO) {
                returnList.add(convertFakeStoreDTOtoProduct(dto));
            }
        }
        return returnList;
    }

    @Override
    public Product replaceProductByID(Long id, Product product) {

        FakeStoreDTO fakeStoreDTO = convertProductToFakeStoreDTO(product);
        // This returns void, so using internal functionality
        // to avoid overload of doing a get after put
        // Less network traffic
//        restTemplate.put(
//                "https://fakestoreapi.com/products/" + id,
//                product,
//                Product.class);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreDTO, FakeStoreDTO.class);
        HttpMessageConverterExtractor<FakeStoreDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreDTO.class, restTemplate.getMessageConverters());
         FakeStoreDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                 HttpMethod.PUT, requestCallback, responseExtractor);
        if (response != null) {
            return convertFakeStoreDTOtoProduct(response);
        }
        return null;
    }


}
