package com.anemchenko.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private Long id;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Role_Id"))
    private Collection<Role> roles;

    @CreationTimestamp
    @Column(name = "Created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "Modified_at")
    private LocalDateTime modifiedAt;

}
