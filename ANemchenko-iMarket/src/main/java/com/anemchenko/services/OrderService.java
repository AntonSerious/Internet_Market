package com.anemchenko.services;


import com.anemchenko.dto.OrderItemDto;
import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.*;
import com.anemchenko.repositories.OrderRepository;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;


    @Transactional
    public void createOrder(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя при оформлении заказа. Имя пользователя: " + principal.getName()));
        Cart cart = cartService.getCurrentCart(cartService.getCartUuidFromSuffix(user.getUsername()));
        Order order = new Order();
        order.setUser(user);
        order.setOrderPrice(cart.getPrice());
        List<OrderItem> items = new ArrayList<>();
        for(OrderItemDto o : cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(o.getPrice());
            orderItem.setPricePerProduct(o.getPricePerProduct());
            orderItem.setQuantity(o.getQuantity());
            orderItem.setProduct(productService.findById(o.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Product not found")));
            items.add(orderItem);
        }
        order.setItems(items);
        orderRepository.save(order);
        cart.clear();
        cartService.updateCart(cartService.getCartUuidFromSuffix(user.getUsername()), cart);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<Order> findByUserName(String customerName){
        return orderRepository.findByUser_Username(customerName);
    }

    public boolean isProductOrdered(String userName, Long productId) {
        if(orderRepository.totalProductsBought(userName, productId) > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
