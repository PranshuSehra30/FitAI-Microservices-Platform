package com.pranshudev.userservice.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String keycloakId;
    private String firstName;
    private String LastName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role= UserRole.USER;

    @CreationTimestamp
    private LocalDateTime updatedAt;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
