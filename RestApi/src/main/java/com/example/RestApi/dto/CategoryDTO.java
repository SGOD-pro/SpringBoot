package com.example.RestApi.dto;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private String name;
    private List<ProductsDTO> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductsDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDTO> products) {
        this.products = products;
    }
}
