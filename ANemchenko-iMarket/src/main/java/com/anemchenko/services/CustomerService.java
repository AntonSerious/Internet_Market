package com.anemchenko.services;

import com.anemchenko.model.Customer;
import com.anemchenko.repositories.CustomerDao;
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

    public CustomerDao getCustomerById(Long id){
        return customerDao.getCustomerById(id);
    }
    public List<Customer> getAllCustomers(){
        return customerDao.getAllCustomers();
    }

    public List<com.anemchenko.repositories.custBuyingsInfoDao> findCustomerDetailsById(Long id) {
        return customerDao.findCustomersDetailsById(id);
    }
}
