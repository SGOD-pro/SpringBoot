package com.example.RestApi.services;

import com.example.RestApi.dto.CategoryDTO;
import com.example.RestApi.entity.Category;
import com.example.RestApi.mapper.CategoryMapper;
import com.example.RestApi.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category=CategoryMapper.toCategoryEntity(categoryDTO);
        category= categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    //get all category
    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }

    //get category by id
    public CategoryDTO getCategoryById(Long id){
        Category category=categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toCategoryDTO(category);
    }
    //delete category
    public boolean deleteCategory(Long id){
       try {
           categoryRepository.deleteById(id);
           return true;
       } catch (Exception e) {
      return false;
       }
    }
}

