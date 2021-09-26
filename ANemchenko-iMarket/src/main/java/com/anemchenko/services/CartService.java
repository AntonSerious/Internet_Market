package com.anemchenko.services;

import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.Product;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.security.Principal;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private static final String REDIS_CART_PREFIX = "WEB_APP_MARET_CART_";
    private final RedisTemplate<String, Object> redisTemplate;
    private String getCartKey(Principal principal, String uuid){
        if(principal != null){
            return REDIS_CART_PREFIX + principal.getName();
        }
        return REDIS_CART_PREFIX + uuid;
    }


    public Cart getCartForCurrentUser(Principal principal, String uuid){
        String cartKey = getCartKey(principal, uuid);
        if(!redisTemplate.hasKey(cartKey)){
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        return cart;
    }

    public Cart getCartByKey(String key){
        if(!redisTemplate.hasKey(REDIS_CART_PREFIX + key)){
            redisTemplate.opsForValue().set(REDIS_CART_PREFIX + key, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(REDIS_CART_PREFIX + key);
    }

    public void addToCart(Principal principal, String uuid, Long productId) {
        Cart cart = getCartForCurrentUser(principal, uuid);
        if(cart.add(productId)){
            updateCurrentCart(principal, uuid, cart);
            return;
        }
        Product p = productService.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Unable add product tp cart. Product not found: " +productId));
        cart.add(p);
        updateCurrentCart(principal, uuid, cart);
    }
    public void updateCurrentCart(Principal principal, String uuid, Cart cart){
        String cartKey = getCartKey(principal, uuid);
        redisTemplate.opsForValue().set(cartKey, cart);
    }
    public void updateCartByKey(String key, Cart cart){
        redisTemplate.opsForValue().set(REDIS_CART_PREFIX + key, cart);
    }
    public void clear(Principal principal, String uuid, Long id) {
        Cart cart = getCartForCurrentUser(principal, uuid);
        if(id.equals(0L)){
            cart.clear();
        }else {
            cart.delete(id);
        }
    }

    public void remove(Principal principal, String uuid, Long id) {
        Cart cart = getCartForCurrentUser(principal, uuid);
        cart.remove(id);
        updateCurrentCart(principal, uuid, cart);
    }

    public void clearCurrentCart(Principal principal, String uuid) {
        Cart cart = getCartForCurrentUser(principal, uuid);
        cart.clear();
        updateCurrentCart(principal, uuid, cart);
    }

    public void merge(Principal principal, String uuid){
        Cart guestCart = getCartByKey(uuid);
        Cart userCart = getCartByKey(principal.getName());

        userCart.merge(guestCart);

        updateCartByKey(principal.getName(), userCart);
        updateCartByKey(uuid, guestCart);
    }
}
