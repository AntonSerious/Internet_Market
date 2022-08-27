package com.anemchenko.api.dtos;

import java.util.List;

public class CartDto {
    private List<OrderItemDto> items;
    private Double totalPrice;

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto() {
    }

    public CartDto(List<OrderItemDto> items, Double totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
}
