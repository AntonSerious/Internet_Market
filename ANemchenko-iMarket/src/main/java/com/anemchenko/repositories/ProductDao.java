package com.anemchenko.repositories;

import com.anemchenko.ANemchenkoIMarketApplication;
import com.anemchenko.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
    public static SessionFactory factory;

    public ProductDao(Object o, String title, int price) {
        this.title = title;
        this.price = price;
    }

    public static void init(){
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;



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
        init();
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            ProductDao productDao = session.get(ProductDao.class, id );
            System.out.println(productDao);
            session.getTransaction().commit();
            return new Product(productDao.getId(), productDao.getTitle(), productDao.getPrice());
        } finally {
            shutdown();
        }
    }
    public void saveOrUpdate(Product product){
        init();
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Long id = product.getId();
            String title = product.getTitle();
            int price = product.getPrice();
            session.saveOrUpdate(new ProductDao(id, title, price));
            session.getTransaction().commit();
        } finally {
            shutdown();
        }
    }

    public static void shutdown(){
        factory.close();
    }
    public List<Product> findAll(){
        init();
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("from ProductDao").getResultList();
            session.getTransaction().commit();
            return products;
        } finally {
            shutdown();
        }
    }
    public void deleteById(Long id){
        init();
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            ProductDao productDao = session.get(ProductDao.class, id );
            session.delete(productDao);
            session.getTransaction().commit();
        } finally {
            shutdown();
        }
    }

}
