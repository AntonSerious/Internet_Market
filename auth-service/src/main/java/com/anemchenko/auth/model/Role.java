package com.anemchenko.auth.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @CreationTimestamp
    @Column(name = "Created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "Modified_at")
    private LocalDateTime updatedAt;
}
