package com.example.RestApi.mapper;


import com.example.RestApi.dto.ProductsDTO;
import com.example.RestApi.entity.Category;
import com.example.RestApi.entity.Product;

//Here we write the mapper logic where we map the entity to the dto

public class ProductMapper {


    //Here we map the dto to the entity
    public static Product toProductEntity(ProductsDTO productsDTO, Category category) {
        Product product = new Product();
        product.setName(productsDTO.getName());
        product.setPrice(productsDTO.getPrice());
        product.setDetails(productsDTO.getDetails());
        product.setCategory(category);
        return product;
    }


    //Here we map the entity to the dto. Entity save in the db, and return the dto
    public static ProductsDTO toProductDTO(Product product) {
        return new ProductsDTO(
                product.getId(),
                product.getPrice(),
                product.getDetails(),
                product.getName(),
                product.getCategory().getId()
        );
    }

}
