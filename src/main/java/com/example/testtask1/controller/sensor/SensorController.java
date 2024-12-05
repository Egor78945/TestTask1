package com.example.testtask1.controller.sensor;

import com.example.testtask1.annotation.exception.handler.SensorControllerExceptionHandler;
import com.example.testtask1.model.dto.SensorModel;
import com.example.testtask1.service.sensor.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
@SensorControllerExceptionHandler
@Tag(name = "Sensor API")
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/registration")
    @Operation(summary = "Registering a new sensor", description = "Register a new sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensor successfully registered"),
            @ApiResponse(responseCode = "400", description = "Sensor is already exists")
    })
    public ResponseEntity<String> addSensor(@RequestBody SensorModel sensorModel){
        sensorService.saveSensor(sensorModel);
        return ResponseEntity.ok("Sensor registered.");
    }
}
