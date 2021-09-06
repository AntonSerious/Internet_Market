package com.anemchenko.services;

import com.anemchenko.model.Customer;
import com.anemchenko.model.Customer_x_Product;
import com.anemchenko.repositories.CustomerRepository;
import com.anemchenko.repositories.Customer_x_ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final Customer_x_ProductRepository customer_x_productRepository;

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).get();
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public List<Customer_x_Product> findCustomerDetailsById(Long id) {
        return customer_x_productRepository.findByCustomer_customerId(id);
    }

    public Optional<Customer> getCustomerByName(String customerName) {
        return customerRepository.findCustomerByName(customerName);
    }
}
