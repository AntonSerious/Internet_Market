package com.anemchenko.model;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Customer_x_Product> buyingsOfCostumer;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Customer_x_Product> getBuyingsOfCostumer() {
        return buyingsOfCostumer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuyingsOfCostumer(List<Customer_x_Product> buyingsOfCostumer) {
        this.buyingsOfCostumer = buyingsOfCostumer;
    }
}
