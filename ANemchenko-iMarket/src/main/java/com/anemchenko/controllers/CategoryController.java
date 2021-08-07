package com.anemchenko.controllers;


import com.anemchenko.dto.CategoryDto;
import com.anemchenko.model.Category;
import com.anemchenko.services.CategoryService;
import com.anemchenko.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id){
        Category c = categoryService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found: " + id));
        return new CategoryDto(c);
    }


}
