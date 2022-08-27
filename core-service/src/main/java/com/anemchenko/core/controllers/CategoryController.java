package com.anemchenko.core.controllers;

import com.anemchenko.api.dtos.CategoryDto;
import com.anemchenko.api.exceptions.ResourceNotFoundException;
import com.anemchenko.core.services.CategoryService;
import com.anemchenko.core.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final Converter converter;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return converter.categoryToDto(categoryService.findByIdWithProducts(id).orElseThrow(() -> new ResourceNotFoundException("Category id = " + id + " not found")));
    }
}
