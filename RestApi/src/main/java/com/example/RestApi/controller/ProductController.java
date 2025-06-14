package com.example.RestApi.controller;

import com.example.RestApi.dto.ProductsDTO;
import com.example.RestApi.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;
    //get all products
    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    //get product by id
    //create product
    @PostMapping("/create")
    public ResponseEntity<ProductsDTO> createProduct(@RequestBody ProductsDTO productsDTO){
        return new ResponseEntity<>(productService.createproduct(productsDTO), HttpStatus.CREATED);
    }


    //update product
    //delete product

}
