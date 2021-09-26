package com.anemchenko.controllers;


import com.anemchenko.dto.CategoryDto;
import com.anemchenko.dto.StringResponse;
import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.Category;
import com.anemchenko.model.Product;
import com.anemchenko.services.CartService;
import com.anemchenko.services.CategoryService;
import com.anemchenko.services.ProductService;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    //private final Cart cart;
    //private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/generate")
    public StringResponse generateCartUuid(){
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/merge")
    public void mergeCarts(Principal principal, @PathVariable String uuid){
        //TODO исправить- чтобы нельзя было вызвать это без токена
        cartService.merge(principal, uuid);
    }

    @GetMapping("/{uuid}")
    public Cart getCartForCurrentUser(Principal principal, @PathVariable String uuid){
        return cartService.getCartForCurrentUser(principal, uuid);
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId){
        cartService.addToCart(principal, uuid, productId);


    }

    @GetMapping("/{uuid}/clear/{id}")
    public void delete(Principal principal, @PathVariable String uuid, @PathVariable Long id){
        cartService.clear(principal, uuid, id);

    }

    @GetMapping("/{uuid}/remove/{id}")
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long id){
        cartService.remove(principal, uuid, id);

    }


}
