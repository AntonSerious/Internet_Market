package com.anemchenko.dto;

import com.anemchenko.model.Order;
import com.anemchenko.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long orderId;
    private BigDecimal price;
    private List<OrderItemDto> items;

    public OrderDto(Order order){
        this.orderId = order.getOrderId();
        this.price = order.getOrderPrice();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
