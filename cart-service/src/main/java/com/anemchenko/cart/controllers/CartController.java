package com.anemchenko.cart.controllers;

import com.anemchenko.api.dtos.CartDto;
import com.anemchenko.api.dtos.ProductDto;
import com.anemchenko.api.dtos.StringResponse;
import com.anemchenko.cart.integration.ProductServiceIntegration;
import com.anemchenko.cart.services.CartService;
import com.anemchenko.cart.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final ProductServiceIntegration productServiceIntegration;

    @GetMapping("/{uuid}")
    public CartDto getCart(@RequestHeader(required = false) String username, @PathVariable String uuid){
        Cart cart = cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
        return new CartDto(cart.getItems(), cart.getTotalPrice());
    }
    @GetMapping("/generate")
    public StringResponse generateCartUuid(){
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        //System.out.println(product.getTitle());
        cartService.addToCart(getCurrentCartUuid(username, uuid), product);
    }
    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }
    @GetMapping("/{uuid}/remove/{id}")
    public void remove(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long id){
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), id);
    }
    @GetMapping("/{uuid}/merge")
    public void mergeCarts(@RequestHeader String username, @PathVariable String uuid){
        //TODO исправить- чтобы нельзя было вызвать это без токена
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid));
    }

    @GetMapping("/{uuid}/clear")
    public void clear(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    private String getCurrentCartUuid( String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);

    }

}
