package com.anemchenko.repositories;

import com.anemchenko.model.Customer;
import com.anemchenko.utils.DBFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "customers")
public class CustomerDao {
    @Autowired
    @Transient
    private DBFactory dbFactory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customerDao")
    private List<custBuyingsInfoDao> buyDetails;

    public CustomerDao() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<custBuyingsInfoDao> getBuyDetails() {
        return buyDetails;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Customer> getAllCustomers() {
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Customer> customers = session.createQuery("from CustomerDao").getResultList();
            session.getTransaction().commit();
            return customers;
        }
    }


    public List<custBuyingsInfoDao> findCustomersDetailsById(Long id) {
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<custBuyingsInfoDao> buyDetails = session.get(CustomerDao.class, id).getBuyDetails();
            System.out.println(buyDetails);
            session.getTransaction().commit();
            return buyDetails;
        }
    }

    public CustomerDao getCustomerById(Long id) {
        try(Session session = dbFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            CustomerDao customerDao = session.get(CustomerDao.class, id);
            session.getTransaction().commit();
            return customerDao;
        }
    }
}
