package com.anemchenko.services;

import com.anemchenko.model.Product;
import com.anemchenko.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id);
    }
    public void saveNewProduct(String title, int price){
        Product product = new Product(null, title, price);
        productRepository.save(product);
    }
}
