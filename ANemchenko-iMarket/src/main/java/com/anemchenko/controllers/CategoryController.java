package com.anemchenko.controllers;


import com.anemchenko.dto.CategoryDto;
import com.anemchenko.model.Category;
import com.anemchenko.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id){
        return new CategoryDto(categoryService.findById(id));
    }


}
