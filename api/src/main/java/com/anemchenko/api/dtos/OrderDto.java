package com.anemchenko.api.dtos;

import java.util.List;

public class OrderDto {
    private Long id;
    private List<OrderItemDto> items;
    private String address;
    private String phone;
    private Double price;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDto() {
    }

    public OrderDto(Long id, List<OrderItemDto> items, String address, String phone, Double price, String status) {
        this.id = id;
        this.items = items;
        this.address = address;
        this.phone = phone;
        this.price = price;
        this.status = status;
    }
}
