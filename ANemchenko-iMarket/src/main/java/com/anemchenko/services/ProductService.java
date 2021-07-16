package com.anemchenko.services;

import com.anemchenko.model.Product;
import com.anemchenko.repositories.ProductDao;
import com.anemchenko.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll(){
        return productDao.findAll();
    }

    public Product findById(Long id){
        return productDao.findById(id);
    }
    public void saveNewProduct(Long id, String title, int price){

        Product product = new Product(id, title, price);
        productDao.saveOrUpdate(product);
    }

    public void deleteProductById(Long id) {
        productDao.deleteById(id);
    }
}
