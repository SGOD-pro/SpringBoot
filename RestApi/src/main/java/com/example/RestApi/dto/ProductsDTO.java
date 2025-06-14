package com.example.RestApi.dto;

import lombok.*;


public class ProductsDTO {


    public ProductsDTO(Long id, int price, String details, String name, Long category_id) {
        this.id = id;
        this.price = price;
        this.details = details;
        this.name = name;
        this.category_id = category_id;
    }

    private Long id;
    private int price;
    private String details;
    private String name;

    private Long category_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
