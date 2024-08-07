package com.example.capstone1_e_commerce_website.Controller;

import com.example.capstone1_e_commerce_website.Api.ApiResponse;
import com.example.capstone1_e_commerce_website.Model.Product;
import com.example.capstone1_e_commerce_website.Service.ProductService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getAllProduct() {
        ArrayList<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);

    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid@RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse( "Product added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id ,@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean productUpdated = productService.updateProduct(id,product);
        if (productUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse( "Product updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse( "Product not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        boolean productDeleted = productService.deleteProduct(id);
        if (productDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse( "Product deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse( "Product not found"));
    }
    @GetMapping("/getproductbycategort/{categoryId}")
    public ResponseEntity getProductByCategory(@PathVariable int categoryId) {

        ArrayList<Product> products = productService.getProductsByCategory(categoryId);
        if (products.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
        }
        return ResponseEntity.status(200).body(products);
    }


}
