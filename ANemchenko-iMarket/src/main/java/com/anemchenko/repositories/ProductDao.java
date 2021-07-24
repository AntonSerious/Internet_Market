package com.anemchenko.repositories;

import com.anemchenko.model.Customer_x_Product;
import com.anemchenko.model.Product;
import com.anemchenko.utils.DBFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao{
    @Autowired
    private DBFactory dbFactory;

    public ProductDao() {
    }

    public Product findById(Long id){
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id );
            System.out.println(product);
            session.getTransaction().commit();
            return product;
        }
    }
    public void save(Product product){
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public List<Product> findAll(){
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }
    public void deleteById(Long id){
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id );
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public List<Customer_x_Product> findAllCustomersByProductId(Long id) {
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Customer_x_Product> customers = session.get(Product.class, id).getCustomersOfProduct();
            System.out.println(customers);
            session.getTransaction().commit();
            return customers;
        }
    }

}
