package com.example.testtask1.service.sensor;

import com.example.testtask1.model.dto.SensorModel;
import com.example.testtask1.model.entity.Sensor;
import com.example.testtask1.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public void saveSensor(SensorModel sensorModel){
        if(!existsSensorByName(sensorModel.getName())) {
            sensorRepository.save(new Sensor(sensorModel.getName()));
        } else {
            throw new RuntimeException("Sensor already exists.");
        }
    }

    public Optional<Sensor> getSensorByName(String name){
        return sensorRepository.findSensorByName(name);
    }

    public boolean existsSensorByName(String name){
        return sensorRepository.existsSensorByName(name);
    }
}
