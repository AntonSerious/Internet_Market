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
    private final CartService cartService;

    @GetMapping("/{uuid}")
    public Cart getCartForCurrentUser(Principal principal, @PathVariable String uuid){
        return cartService.getCurrentCart(getCurrentCartUuid(principal, uuid));
    }
    @GetMapping("/generate")
    public StringResponse generateCartUuid(){
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId){
        cartService.addToCart(getCurrentCartUuid(principal, uuid), productId);
    }
    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(principal, uuid), productId);
    }
    @GetMapping("/{uuid}/remove/{id}")
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long id){
        cartService.removeItemFromCart(getCurrentCartUuid(principal, uuid), id);
    }
    @GetMapping("/{uuid}/merge")
    public void mergeCarts(Principal principal, @PathVariable String uuid){
        //TODO исправить- чтобы нельзя было вызвать это без токена
        cartService.merge(
                getCurrentCartUuid(principal, null),
                getCurrentCartUuid(null, uuid));
    }

    @GetMapping("/{uuid}/clear")
    public void clear(Principal principal, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(principal, uuid));
    }

    private String getCurrentCartUuid(Principal principal, String uuid) {
        if (principal != null) {
            return cartService.getCartUuidFromSuffix(principal.getName());
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
