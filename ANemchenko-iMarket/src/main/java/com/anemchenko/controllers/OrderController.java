package com.anemchenko.controllers;

import com.anemchenko.dto.OrderDto;
import com.anemchenko.dto.OrderItemDto;
import com.anemchenko.dto.ProductDto;
import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.*;
import com.anemchenko.services.CategoryService;
import com.anemchenko.services.OrderService;
import com.anemchenko.services.ProductService;
import com.anemchenko.services.UserService;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/create")
    public void createOrder(Principal principal){
        //User user = userService.findByUsername(principal.getName()).get();
        //System.out.println(user.getEmail());
        orderService.createOrder(principal);
    }

    @GetMapping()
    public List<OrderDto> getAllOrders(Principal principal){
        return orderService.findByCustomerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
