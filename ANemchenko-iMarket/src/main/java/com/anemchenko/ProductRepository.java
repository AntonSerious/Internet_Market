package com.anemchenko;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class ProductRepository {
    private ArrayList<Product> productList;


    public ProductRepository() {
    }

    @PostConstruct
    public void init(){
        this.productList = new ArrayList<Product>(Arrays.asList(
                new Product(1, "Milk", 20L),
                new Product(2, "Juice", 10L),
                new Product(3, "Potato", 5L),
                new Product(4, "Orange", 7L),
                new Product(5, "Bread", 4L)
                ));
    }

    public Product findById(int id)
    {
        for(Product p : productList){
            if(p.getId() == id){
                return p;
            }
        }
        throw new RuntimeException("User not found");

    }


    public void showProducts(){
        System.out.println("ID Title Cost ");
        for (Product p: productList
             ) {
            System.out.println(p);
        }
    }
    public void addProduct(int id, String title, double cost){
        productList.add(new Product(id, title, cost));
        System.out.println("Prdoduct: " + id + " " + title + " " + cost + " has been added");
    }
    public ArrayList<Product> getProductList(){
        return this.productList;
    }

}
