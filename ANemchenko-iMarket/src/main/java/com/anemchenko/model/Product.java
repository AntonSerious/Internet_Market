package com.anemchenko.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Id")
    private Long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Price")
    private int price;

    @OneToMany(mappedBy = "product")
    private List<Customer_x_Product> customersOfProduct;

    public Product(String title, int price){
        this.title = title;
        this.price = price;
    }
    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

}
