package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    //Build get category REST API
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "id") Long categoryId){
        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }
    //Build get category REST API
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> allCategories = categoryService.getAllCategory();
        return ResponseEntity.ok(allCategories);
    }
    //Build update category REST API
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("id") Long categoryId){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(updatedCategory);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
