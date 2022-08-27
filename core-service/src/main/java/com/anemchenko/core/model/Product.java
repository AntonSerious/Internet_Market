package com.anemchenko.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Double price;

    @ManyToOne
    @JoinColumn(name = "Category_Id")
    private Category category;

    @CreationTimestamp
    @Column(name = "Created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "Modified_at")
    private LocalDateTime updatedAt;
}
