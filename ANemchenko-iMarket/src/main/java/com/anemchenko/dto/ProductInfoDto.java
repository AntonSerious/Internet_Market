package com.anemchenko.dto;

import com.anemchenko.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductInfoDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;

    public ProductInfoDto(Product product){
        this.id = product.getProductId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}
