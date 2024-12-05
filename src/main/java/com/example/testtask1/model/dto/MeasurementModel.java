package com.example.testtask1.model.dto;

import lombok.Data;

@Data
public class MeasurementModel {
    private Double value;
    private Boolean raining;
    private SensorModel sensor;
}
