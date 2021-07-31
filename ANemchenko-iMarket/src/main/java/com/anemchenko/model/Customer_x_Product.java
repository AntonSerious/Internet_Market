package com.anemchenko.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "Product_Id")
    @JsonIgnore
    private Product product;

    @Column(name = "Buy_Price")
    private long buyPrice;

}
