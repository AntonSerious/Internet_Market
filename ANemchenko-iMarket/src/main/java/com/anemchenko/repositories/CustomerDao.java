package com.anemchenko.repositories;

import com.anemchenko.model.Customer;
import com.anemchenko.model.Customer_x_Product;
import com.anemchenko.utils.DBFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CustomerDao {
    @Autowired
    private DBFactory dbFactory;

    public CustomerDao() {
    }


    public List<Customer> getAllCustomers() {
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Customer> customers = session.createQuery("from Customer").getResultList();
            session.getTransaction().commit();
            return customers;
        }
    }


    public List<Customer_x_Product> findBuyingDetailsByCustomerId(Long id) {
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Customer_x_Product> buyDetails = session.get(Customer.class, id).getBuyingsOfCustomer();
            System.out.println(buyDetails);
            session.getTransaction().commit();
            return buyDetails;
        }
    }

    public Customer getCustomerById(Long id) {
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.getTransaction().commit();
            return customer;
        }
    }
}
