package com.anemchenko.model;

import com.anemchenko.repositories.ProductDao;

public class Product extends ProductDao {
    private Long id;
    private String title;
    private int price;

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
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
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

}
