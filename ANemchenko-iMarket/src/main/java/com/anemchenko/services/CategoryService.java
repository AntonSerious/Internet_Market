package com.anemchenko.services;


import com.anemchenko.model.Category;
import com.anemchenko.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public Category findByTitle(String categoryTitle){
        Category cat = categoryRepository.findByTitle(categoryTitle);
        System.out.println(cat.getId());
        return cat;}
}
