package com.example.RestApi.controller;
import com.example.RestApi.dto.CategoryDTO;
import com.example.RestApi.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categorySrv;

    public CategoryController(CategoryService categorySrv) {
        this.categorySrv = categorySrv;
    }

    //create category
//    @PostMapping("/create")
//    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
//        return new ResponseEntity<>(categorySrv.createCategory(categoryDTO), HttpStatusCode.valueOf(201));
//    }

//    BEfore GlobalEXception
//    @PostMapping("/create")
//    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
//        try{
//            return new ResponseEntity<>(categorySrv.createCategory(categoryDTO), HttpStatusCode.valueOf(201));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//    }
//After Global Exception
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categorySrv.createCategory(categoryDTO),HttpStatus.CREATED);
    }
    //get all category
    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categorySrv.getAllCategories();
    }

    //get category by id
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categorySrv.getCategoryById(id);
    }

    //delete category
    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable Long id){
        return categorySrv.deleteCategory(id);
    }
}
