package com.example.testtask1.model.dto;

import lombok.Data;

@Data
public class SensorModel {
    private String name;

    public SensorModel(String name) {
        this.name = name;
    }

    public SensorModel() {
    }
}
