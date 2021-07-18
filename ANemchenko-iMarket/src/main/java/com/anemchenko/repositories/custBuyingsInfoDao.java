package com.anemchenko.repositories;

import com.anemchenko.utils.DBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "cust_buyings_info")
public class custBuyingsInfoDao {

    @Autowired
    @Transient
    DBFactory dbFactory;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRecord")
    private long id;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private CustomerDao customerDao;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductDao productDao;

    @Column(name = "buyPrice")
    private long buyPrice;


    public long getId() {
        return id;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public long getBuyPrice() {
        return buyPrice;
    }
}
