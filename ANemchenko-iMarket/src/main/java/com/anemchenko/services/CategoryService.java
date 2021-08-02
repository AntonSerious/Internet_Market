package com.anemchenko.services;


import com.anemchenko.model.Category;
import com.anemchenko.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }

    public Category findByTitle(String categoryTitle){
        Category cat = categoryRepository.findByTitle(categoryTitle);
        System.out.println(cat.getId());
        return cat;}
}
