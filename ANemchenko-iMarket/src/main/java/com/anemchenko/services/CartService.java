package com.anemchenko.services;

import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.Product;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final Cart cart;

//    @PostConstruct
//    public void init(){
//        cart = new Cart();
//    }

    public Cart getCartForCurrentUser(){
        return cart;
    }

    public void addToCart(Long productId) {
        if(cart.add(productId)){
            return;
        }
        Product p = productService.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Unable add product tp cart. Product not found: " +productId));
        cart.add(p);
    }

    public void clear(Long id) {
        if(id.equals(0L)){
            cart.clear();
        }else {
            cart.delete(id);
        }
    }

    public void remove(Long id) {
        cart.remove(id);
    }
}
