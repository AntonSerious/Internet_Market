package com.anemchenko.dto;

import com.anemchenko.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
    private int quantity;

    public OrderItemDto(Product product){
        this.productId = product.getProductId();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
        this.productTitle = product.getTitle();
    }

    public void incrementQuantity(int amount){
        quantity += amount;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }
}
