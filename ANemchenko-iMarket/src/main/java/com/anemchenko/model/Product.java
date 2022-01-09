package com.anemchenko.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Id")
    private Long productId;

    @Column(name = "Title")
    private String title;

    @Column(name = "Price")
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Customer_x_Product> customersOfProduct;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductComment> productComments;

    @CreationTimestamp
    @Column(name = "Created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "Modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "Description")
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Category_Id")
    private Category category;



}
