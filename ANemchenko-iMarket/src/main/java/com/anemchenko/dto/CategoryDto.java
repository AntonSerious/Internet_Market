package com.anemchenko.dto;

import com.anemchenko.model.Category;
import com.anemchenko.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;
    private List<String> products;

    public CategoryDto(Category category){
        this.id = category.getId();
        this.title = category.getTitle();
        this.products = category.getProducts().stream()
                .map(Product::getTitle)
                .collect(Collectors.toList());
    }
}
