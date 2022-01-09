package com.anemchenko.controllers;

import com.anemchenko.dto.OrderDto;
import com.anemchenko.services.OrderService;
import com.anemchenko.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
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
        return orderService.findByUserName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
    @GetMapping("/isProductOrdered")
    public boolean isProductOrdered (Principal principal, @RequestParam (name = "p") Long productId){
        return orderService.isProductOrdered(principal.getName(), productId);
    }

}
