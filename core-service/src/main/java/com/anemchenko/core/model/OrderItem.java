package com.anemchenko.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders_details")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Detail_Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Order_Id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "Product_Id")
    private Product product;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price_Per_Product")
    private Double pricePerProduct;

    @Column(name = "Price")
    private Double price;

    @CreationTimestamp
    @Column(name = "Created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "Modified_at")
    private LocalDateTime updatedAt;
}
