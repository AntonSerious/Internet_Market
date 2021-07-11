package com.anemchenko.repositories;

import com.anemchenko.model.Product;
import com.anemchenko.utils.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> items;

    @PostConstruct
    public void init(){
        items = new ArrayList<>(Arrays.asList(
                new Product(1L, "apple", 30),
                new Product(2L, "Milk", 5),
                new Product(3L, "Bread", 50),
                new Product(4L, "Chees", 40)
        ));
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(items);
    }

    public Product findById(Long id){
        for(Product p: items){
            if(p.getId().equals(id)){
                return p;
            }
        }
        throw new ResourceNotFoundException();
    }

    public void save(Product product){
        product.setId(items.stream().mapToLong(Product::getId).max().getAsLong() + 1L);
        if(product.getPrice()<= 0){
            return;
        }
        items.add(product);
    }
}
