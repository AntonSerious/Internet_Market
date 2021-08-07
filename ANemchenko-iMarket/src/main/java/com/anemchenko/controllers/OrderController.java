package com.anemchenko.controllers;

import com.anemchenko.dto.OrderDto;
import com.anemchenko.dto.OrderItemDto;
import com.anemchenko.dto.ProductDto;
import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.Order;
import com.anemchenko.model.OrderItem;
import com.anemchenko.model.Product;
import com.anemchenko.services.CategoryService;
import com.anemchenko.services.OrderService;
import com.anemchenko.services.ProductService;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/create")
    public void createOrder(){
        orderService.createOrder();
    }

    @GetMapping()
    public List<OrderDto> getAllOrders(){
        return orderService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
