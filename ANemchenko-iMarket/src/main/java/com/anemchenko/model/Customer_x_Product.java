package com.anemchenko.model;

import com.anemchenko.repositories.CustomerDao;
import com.anemchenko.repositories.ProductDao;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "cust_buyings_info")
public class Customer_x_Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRecord")
    private long id;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @Column(name = "buyPrice")
    private long buyPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }
    public Product getProduct() {
        return product;
    }

    public long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(long buyPrice) {
        this.buyPrice = buyPrice;
    }
}
