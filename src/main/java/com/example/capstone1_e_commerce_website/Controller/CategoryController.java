package com.example.capstone1_e_commerce_website.Controller;

import com.example.capstone1_e_commerce_website.Api.ApiResponse;
import com.example.capstone1_e_commerce_website.Model.Category;
import com.example.capstone1_e_commerce_website.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getCategory(){
        ArrayList<Category> categories= categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);

    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable int id, @Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean categoryUpdated = categoryService.updateCategory(id, category);
        if(categoryUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Category updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found"));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){
        boolean categoryDeleted = categoryService.deleteCategory(id);
        if(categoryDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Category deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found"));

    }
}
