package com.anemchenko.repositories;

import com.anemchenko.model.Customer;
import com.anemchenko.model.Customer_x_Product;
import com.anemchenko.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Customer_x_ProductRepository extends JpaRepository<Customer_x_Product, Long> {
    List<Customer_x_Product> findByProduct_Id(Long id);


    List<Customer_x_Product> findByCustomer_Id(Long id);
}
