package com.anemchenko.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "cust_buyings_info")
@Data
@NoArgsConstructor
public class Customer_x_Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Record_Id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Customer_Id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "Product_Id")
    private Product product;

    @Column(name = "Buy_Price")
    private long buyPrice;

}
