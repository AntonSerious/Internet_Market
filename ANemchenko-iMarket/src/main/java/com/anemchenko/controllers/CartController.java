package com.anemchenko.controllers;


import com.anemchenko.dto.CategoryDto;
import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.Category;
import com.anemchenko.model.Product;
import com.anemchenko.services.CategoryService;
import com.anemchenko.services.ProductService;
import com.anemchenko.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final Cart cart;
    private final ProductService productService;

    @GetMapping
    public Cart getCart(){
        return cart;
    }

    @GetMapping("/add/{productId}")
    public void add(@PathVariable Long productId){
        if(cart.add(productId)){
            return;
        }
        Product p = productService.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Unable add product tp cart. Product not found: " +productId));
        cart.add(p);
    }

    @GetMapping("/clear/{id}")
    public void delete(@PathVariable Long id){
        if(id.equals(0)){
            cart.clear();
        }else {
            cart.delete(id);
        }
    }

    @GetMapping("/remove/{id}")
    public void remove(@PathVariable Long id){
        cart.remove(id);
    }


}
