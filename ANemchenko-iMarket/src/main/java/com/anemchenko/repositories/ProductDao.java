package com.anemchenko.repositories;

import com.anemchenko.ANemchenkoIMarketApplication;
import com.anemchenko.model.Customer;
import com.anemchenko.model.Product;
import com.anemchenko.utils.DBFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Entity
@Table(name = "products")
public class ProductDao{
    @Autowired
    @Transient
    private DBFactory dbFactory;

    public ProductDao(Object o, String title, int price) {
        this.title = title;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "productDao")
    private List<custBuyingsInfoDao> customerDetails;


    public ProductDao() {

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }




    public Product findById(Long id){
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            ProductDao productDao = session.get(ProductDao.class, id );
            System.out.println(productDao);
            session.getTransaction().commit();
            return new Product(productDao.getId(), productDao.getTitle(), productDao.getPrice());
        }
    }
    public void saveOrUpdate(Product product){
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            Long id = product.getId();
            String title = product.getTitle();
            int price = product.getPrice();
            session.saveOrUpdate(new ProductDao(id, title, price));
            session.getTransaction().commit();
        }
    }

    public List<Product> findAll(){
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("from ProductDao").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }
    public void deleteById(Long id){
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            ProductDao productDao = session.get(ProductDao.class, id );
            session.delete(productDao);
            session.getTransaction().commit();
        }
    }

    public List<custBuyingsInfoDao> findAllCustomersById(Long id) {
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<custBuyingsInfoDao> customers = session.get(ProductDao.class, id).getCustomers();
            System.out.println(customers);
            session.getTransaction().commit();
            return customers;
        }
    }

    private List<custBuyingsInfoDao> getCustomers() {
        return customerDetails;
    }
}
