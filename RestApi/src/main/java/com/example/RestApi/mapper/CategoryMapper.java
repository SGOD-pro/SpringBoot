package com.example.RestApi.mapper;

import com.example.RestApi.dto.CategoryDTO;
import com.example.RestApi.entity.Category;

public class CategoryMapper {
    public static Category toCategoryEntity(CategoryDTO catDTO){
        if (catDTO == null) {
            throw new IllegalArgumentException("CategoryDTO is null");
        }

        // 🔹 Debugging log
        System.out.println("CategoryDTO Name: " + catDTO.getName());

        Category category = new Category();
        category.setName(catDTO.getName());
        return category;
    }

    public static CategoryDTO toCategoryDTO(Category category){
        if (category==null)
            return null;
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setId(category.getId());
        categoryDTO.setProducts(category.getProducts().stream().map(ProductMapper::toProductDTO).toList());
        return categoryDTO;
    }
}
