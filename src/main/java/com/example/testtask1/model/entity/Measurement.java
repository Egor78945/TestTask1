package com.example.testtask1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "measurement")
@Data
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "sensor_id")
    private Long sensorId;
    @Column(name = "value")
    private Double value;
    @Column(name = "raining")
    private Boolean raining;

    public Measurement(Long sensorId, Double value, Boolean raining) {
        this.sensorId = sensorId;
        this.value = value;
        this.raining = raining;
    }

    public Measurement() {
    }
}
