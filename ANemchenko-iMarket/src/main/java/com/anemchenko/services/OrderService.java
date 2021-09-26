package com.anemchenko.services;


import com.anemchenko.dto.OrderItemDto;
import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.Customer;
import com.anemchenko.model.Order;
import com.anemchenko.model.OrderItem;
import com.anemchenko.model.Product;
import com.anemchenko.repositories.CustomerRepository;
import com.anemchenko.repositories.OrderRepository;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    private final CartService cartService;


    @Transactional
    public void createOrder(Principal principal){
        String customerName = principal.getName();
        Order order = new Order();
        Cart cart = cartService.getCartForCurrentUser(principal, null);
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

        }
        Optional<Customer> customer = customerService.getCustomerByName(customerName);
        if(!customer.isPresent()){
            Customer newCust = new Customer();
            newCust.setName(customerName);
            customerRepository.save(newCust);
            order.setCustomer(newCust);
        }else {
            order.setCustomer(customer.get());
        }
        orderRepository.save(order);
        cartService.clearCurrentCart(principal, null);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<Order> findByCustomerName(String customerName){
        return orderRepository.findByCustomer_Name(customerName);
    }

}
