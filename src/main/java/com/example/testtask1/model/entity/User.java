package com.example.testtask1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "#{@environment.getProperty('spring.datasource.user.table.name')}")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
