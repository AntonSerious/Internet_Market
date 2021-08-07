package com.anemchenko.dto;

import com.anemchenko.model.Order;
import com.anemchenko.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long orderId;
    private BigDecimal price;

    public OrderDto(Order order){
        this.orderId = order.getOrderId();
        this.price = order.getOrderPrice();
    }
}
