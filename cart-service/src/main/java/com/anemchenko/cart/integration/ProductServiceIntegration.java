package com.anemchenko.cart.integration;

import com.anemchenko.api.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;


    public ProductDto getProductById(Long productId){
        ProductDto prooduct = productServiceWebClient.get()
                .uri("/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
        return prooduct;
    }
}
