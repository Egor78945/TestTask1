package com.example.testtask1.controller.sensor;

import com.example.testtask1.model.dto.SensorModel;
import com.example.testtask1.service.sensor.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    @PostMapping("/registration")
    public ResponseEntity<String> addSensor(@RequestBody SensorModel sensorModel){
        sensorService.saveSensor(sensorModel);
        return ResponseEntity.ok("Sensor registered.");
    }
}
