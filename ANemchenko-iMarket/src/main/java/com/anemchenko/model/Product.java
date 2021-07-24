package com.anemchenko.model;

import com.anemchenko.repositories.Customer_x_ProductDao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "product")
    private List<Customer_x_Product> customersOfProduct;
    public Product() {
    }

    public Product(String title, int price){
        this.title = title;
        this.price = price;
    }
    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Customer_x_Product> getCustomersOfProduct() {
        return customersOfProduct;
    }

    public void setCustomersOfProduct(List<Customer_x_Product> customersOfProduct) {
        this.customersOfProduct = customersOfProduct;
    }
}
