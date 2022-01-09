package com.anemchenko.services;

import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.Product;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;
import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    @Value("${utils.cart.prefix}")
    private String cartPrefix = "WEB_APP_MARKET_CART_";
    private final RedisTemplate<String, Object> redisTemplate;

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    private String getCartKey(Principal principal, String uuid){
        if(principal != null){
            return cartPrefix + principal.getName();
        }
        return cartPrefix + uuid;
    }


    public Cart getCurrentCart(String cartKey) {
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public Cart getCartByKey(String key){
        if(!redisTemplate.hasKey(cartPrefix + key)){
            redisTemplate.opsForValue().set(cartPrefix + key, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartPrefix + key);
    }

    public void addToCart(String cartKey, Long productId) {
        execute(cartKey, c -> {
            if (!c.add(productId)) {
                c.add(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found id: " + productId)));
            }
        });
    }
    public void updateCart(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }
    public void updateCartByKey(String key, Cart cart){
        redisTemplate.opsForValue().set(cartPrefix + key, cart);
    }
    public void clearCart(String cartKey) {
        execute(cartKey, Cart::clear);
    }

    public void removeItemFromCart(String cartKey, Long productId) {
        execute(cartKey, c -> c.remove(productId));
    }
    public void decrementItem(String cartKey, Long productId) {
        execute(cartKey, c -> c.decrement(productId));
    }
    public void merge(String userCartKey, String guestCartKey){
        Cart guestCart = getCurrentCart(guestCartKey);
        Cart userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);

        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }
    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }
}
