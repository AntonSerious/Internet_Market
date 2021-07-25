package com.anemchenko.repositories;

import com.anemchenko.model.Product;
import com.anemchenko.utils.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(int min);
    List<Product> findAllByPriceLessThanEqual(int max);
    List<Product> findAllByPriceBetween(int min, int max);
}
