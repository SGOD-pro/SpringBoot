package com.example.RestApi.controller;

import com.example.RestApi.dto.ProductsDTO;
import com.example.RestApi.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@EnableMethodSecurity(prePostEnabled = true)
public class ProductController {

    @Autowired
    ProductService productService;

    //get all products
    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    //get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductsDTO> getProductById(@PathVariable Long id){
        return  new ResponseEntity<>(productService.getProductById(id), HttpStatusCode.valueOf(200));
    }
    //create product
    @PreAuthorize("hasAuthority('ROLE_SALER')")
    @PostMapping("/create")
    public ResponseEntity<ProductsDTO> createProduct(@RequestBody ProductsDTO productsDTO){
        return new ResponseEntity<>(productService.createproduct(productsDTO), HttpStatus.CREATED);
    }

    //update product
    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> updateProductById(@PathVariable Long id,@RequestBody ProductsDTO product){
        return  new ResponseEntity<>(productService.update(id,product), HttpStatusCode.valueOf(200));
    }

    //delete product
    @PreAuthorize("hasAuthority('ROLE_SALER')")
    //@PreAuthorize("hasAuthority('ROLE_SALER') or hasAuthority('ROLE_ADMIN')") for both..
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
