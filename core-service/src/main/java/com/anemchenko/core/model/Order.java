package com.anemchenko.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
@NamedEntityGraph(
        name = "orders.for-front",
        attributeNodes = {
                @NamedAttributeNode(value = "items", subgraph = "items-products")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "items-products",
                        attributeNodes = {
                                @NamedAttributeNode("product")
                        }
                )
        }
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Id")
    private Long id;

    @Column(name = "Username")
    private String username;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Status")
    private String status;

    @CreationTimestamp
    @Column(name = "Created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "Modified_at")
    private LocalDateTime updatedAt;
}
