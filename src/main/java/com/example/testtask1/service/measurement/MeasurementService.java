package com.example.testtask1.service.measurement;

import com.example.testtask1.model.dto.MeasurementModel;
import com.example.testtask1.model.entity.Measurement;
import com.example.testtask1.model.entity.Sensor;
import com.example.testtask1.repository.MeasurementRepository;
import com.example.testtask1.service.sensor.SensorService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Tag(name = "Measurement Service", description = "Provide manipulations with measurements")
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    private final JsonMapper jsonMapper;

    public void registerMeasurement(MeasurementModel measurementModel) {
        Sensor sensor = sensorService.getSensorByName(measurementModel.getSensor().getName()).orElseThrow(() ->
                new RuntimeException(String.format("Sensor with name %s is not found.", measurementModel.getSensor().getName())));
        Measurement measurement = new Measurement(sensor.getId(), measurementModel.getValue(), measurementModel.getRaining());
        measurementRepository.save(measurement);
    }

    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    public int getRainingCount() {
        return measurementRepository.findRainingCount();
    }
}
