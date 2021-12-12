package com.anemchenko;

import com.anemchenko.model.Product;
import com.anemchenko.services.CartService;
import com.anemchenko.services.ProductService;
import com.anemchenko.utils.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

@SpringBootTest
public class cartServiceTest {
    @Autowired
    private CartService cartService;

    @MockBean
    private ProductService productService;

    private static final String REDIS_CART_PREFIX = "WEB_APP_MARET_CART_";
    @Test
    public void addToCartTest(){
        Cart cart = new Cart();
        Product testProduct = new Product();
        testProduct.setProductId(7L);
        testProduct.setPrice(BigDecimal.valueOf(100));
        testProduct.setTitle("Test Product");

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "testPrincipal";
            }
        };

        Mockito.doReturn(Optional.of(testProduct)).when(productService).findById(7L);
        //Mockito.doReturn(cart).when(cartService).getCartForCurrentUser(principal, "uuid");

        cartService.addToCart(principal, "uuid", 7L);
        cartService.addToCart(principal, "uuid", 7L);
        Assertions.assertEquals(1, cartService.getCartForCurrentUser(principal,"uuid").getItems().size());

    }
}
