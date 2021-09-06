package com.anemchenko.repositories;

import com.anemchenko.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c join fetch c.buyingsOfCustomer where c.id = :id")
    Optional<Customer> findBuyingsOfCustomer(@Param("id") Long id);

    Optional<Customer> findCustomerByName(String customerName);
}
