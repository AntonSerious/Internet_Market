package com.anemchenko.repositories;

import com.anemchenko.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(int min);
    List<Product> findAllByPriceLessThanEqual(int max);
    List<Product> findAllByPriceBetween(int min, int max);
}
