package com.anemchenko.services;

import com.anemchenko.model.Customer;
import com.anemchenko.model.Customer_x_Product;
import com.anemchenko.repositories.CustomerDao;
import com.anemchenko.repositories.Customer_x_ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer getCustomerById(Long id){
        return customerDao.getCustomerById(id);
    }
    public List<Customer> getAllCustomers(){
        return customerDao.getAllCustomers();
    }

    public List<Customer_x_Product> findCustomerDetailsById(Long id) {
        return customerDao.findBuyingDetailsByCustomerId(id);
    }
}
