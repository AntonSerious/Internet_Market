package com.anemchenko.core.integration;

import com.anemchenko.api.dtos.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getUserCartByUsername(String username){

        CartDto cart = cartServiceWebClient.get()
                .uri("/api/v1/cart/" + username)
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
        return cart;
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("username", username);
//        return restTemplate.exchange(cartServiceUrl, HttpMethod.GET, new HttpEntity(headers), CartDto.class).getBody();
    }
    public void clearUserCart(String username){
        cartServiceWebClient.get()
                .uri("/api/v1/cart/" + username + "/clear")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();

//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("username", username);
//        restTemplate.exchange(cartServiceUrl + "/clear", HttpMethod.GET, new HttpEntity(headers), void.class);
    }
//    private final WebClient productServiceWebClient;
//
//
//    public ProductDto getProductById(Long productId){
//        ProductDto prooduct = productServiceWebClient.get()
//                .uri("/api/v1/products/" + productId)
//                .retrieve()
//                .bodyToMono(ProductDto.class)
//                .block();
//        return prooduct;
//    }
}
