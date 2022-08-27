package com.anemchenko.core.services;

import com.anemchenko.api.dtos.CartDto;
import com.anemchenko.api.dtos.OrderDetailsDto;
import com.anemchenko.api.dtos.OrderDto;
import com.anemchenko.api.dtos.OrderItemDto;
import com.anemchenko.api.exceptions.ResourceNotFoundException;
import com.anemchenko.core.integration.CartServiceIntegration;
import com.anemchenko.core.model.Order;
import com.anemchenko.core.model.OrderItem;
import com.anemchenko.core.repositories.OrderRepository;
import com.anemchenko.core.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
    private final Converter converter;

    @Transactional
    public Order createOrder(String username, OrderDetailsDto orderDetailsDto) {
        CartDto cart = cartServiceIntegration.getUserCartByUsername(username);
        Order order = new Order();
        order.setUsername(username);
        order.setPrice(cart.getTotalPrice());
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto i : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
//            orderItem.setPrice(i.getPrice());
//            orderItem.setPricePerProduct(i.getPricePerProduct());
            orderItem.setQuantity(i.getQuantity());
            orderItem.setProduct(productService.findById(i.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт при оформлении заказа. ID продукта: " + i.getProductId())));
            items.add(orderItem);
        }
        order.setItems(items);
        orderRepository.save(order);
        cartServiceIntegration.clearUserCart(username);
        return order;
    }
    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }
    @Transactional
    public Optional<OrderDto> findDtoByIdAndUsername(Long id, String username){
        return orderRepository.findOneByIdAndUsername(id, username).map(o -> converter.orderToDto(o));
    }
    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    @Transactional
    public Order updateOrderStatus(Long id, String username) {
        Order order = orderRepository.getById(id);
        order.setStatus("To deliver");
        orderRepository.save(order);
        return order;
    }
}
