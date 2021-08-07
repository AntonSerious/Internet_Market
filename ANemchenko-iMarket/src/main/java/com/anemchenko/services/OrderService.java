package com.anemchenko.services;


import com.anemchenko.dto.OrderItemDto;
import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.Order;
import com.anemchenko.model.OrderItem;
import com.anemchenko.model.Product;
import com.anemchenko.repositories.OrderRepository;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final Cart cart;


    @Transactional
    public void createOrder(){
        Order order = new Order();
        order.setOrderPrice(cart.getPrice());
        order.setItems(new ArrayList<>());
        for(OrderItemDto o : cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            Product product = productService.findById(o.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
            orderItem.setPricePerProduct(product.getPrice());
            orderItem.setProduct(product);
            order.getItems().add(orderItem);
            order.setCustomer(customerService.getCustomerById(1L)); //заглушечка на первое время
        }
        orderRepository.save(order);
        cart.clear();
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
}
