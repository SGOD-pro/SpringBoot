package com.example.RestApi.services;


import com.example.RestApi.dto.ProductsDTO;
import com.example.RestApi.entity.Category;
import com.example.RestApi.entity.Product;
import com.example.RestApi.mapper.ProductMapper;
import com.example.RestApi.repository.CategoryRepository;
import com.example.RestApi.repository.ProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ProductsDTO createproduct(ProductsDTO productsDTO) {
        Category category = categoryRepository.findById(productsDTO.getCategory_id()).orElseThrow(
                () -> new RuntimeException("Category not found"));

        Product product = ProductMapper.toProductEntity(productsDTO, category);
        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    public List<ProductsDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }
}
