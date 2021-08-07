package com.anemchenko.model;



import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_Id")
    private Long customerId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Customer_x_Product> buyingsOfCustomer;

    @OneToMany(mappedBy = "orderId")
    private List<Order> customerOrders;

}
