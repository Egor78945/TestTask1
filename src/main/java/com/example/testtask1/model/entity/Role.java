package com.example.testtask1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role")
    private String role;

    public Role(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public Role() {
    }
}
