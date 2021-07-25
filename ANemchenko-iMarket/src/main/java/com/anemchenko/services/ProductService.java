package com.anemchenko.services;

import com.anemchenko.model.Customer_x_Product;
import com.anemchenko.model.Product;
import com.anemchenko.repositories.Customer_x_ProductRepository;
import com.anemchenko.repositories.ProductDao;
import com.anemchenko.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Customer_x_ProductRepository customer_x_productRepository;


    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).get();
    }
    public void saveNewProduct(Long id, String title, int price){

        Product product = new Product(id, title, price);
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Customer_x_Product> findAllCustomersByProductId(Long id){
       return customer_x_productRepository.findByProduct_Id(id);
    }

    public List<Product> findAllProductsByPriceGreaterOrEqualThanMinPrice(int minPrice){
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }
    public List<Product> findAllProductsByPriceLessOrEqualThanMaxPrice(int maxPrice){
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }
    public List<Product> findAllProductsByPriceBetweenMinAndMaxPrice(int minPrice, int maxPrice){
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

}
