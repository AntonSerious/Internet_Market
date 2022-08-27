package com.anemchenko.core.controllers;

import com.anemchenko.api.dtos.OrderDetailsDto;
import com.anemchenko.api.dtos.OrderDto;
import com.anemchenko.api.dtos.StringResponse;
import com.anemchenko.core.services.OrderService;
import com.anemchenko.core.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createOrder(@RequestBody OrderDetailsDto orderDetailsDto, @RequestHeader String username) {
        return new StringResponse(orderService.createOrder(username, orderDetailsDto).getId().toString());
    }

    @GetMapping
    public List<OrderDto> getOrdersForCurrentUser(@RequestHeader String username) {

        return orderService.findAllByUsername(username).stream().map(o -> converter.orderToDto(o)).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public OrderDto getOrderForCurrentUser(@RequestHeader String username, @PathVariable Long id) {

        return orderService.findDtoByIdAndUsername(id, username).get();
    }

    @PutMapping("/{id}")
    public OrderDto setPayedStatus(@RequestHeader String username, @PathVariable Long id) {
        return converter.orderToDto(orderService.updateOrderStatus(id, username));
    }
}
