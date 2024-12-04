package com.example.testtask1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sensor")
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    public Sensor(String name) {
        this.name = name;
    }

    public Sensor() {
    }
}
