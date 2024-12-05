package com.example.testtask1.controller.measurement;

import com.example.testtask1.annotation.exception.handler.MeasurementControllerExceptionHandler;
import com.example.testtask1.model.dto.MeasurementModel;
import com.example.testtask1.model.entity.Measurement;
import com.example.testtask1.service.measurement.MeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
@MeasurementControllerExceptionHandler
@Tag(name = "Measurement API")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Operation(summary = "Register a new measurement", description = "Register a measurement for a sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Measurement successfully pined for a sensor"),
            @ApiResponse(responseCode = "400", description = "Sensor is not found")
    })
    @PostMapping("/add")
    public ResponseEntity<String> registerMeasurement(@RequestBody MeasurementModel measurementModel){
        measurementService.registerMeasurement(measurementModel);
        return ResponseEntity.ok("Measurement registered.");
    }

    @Operation(summary = "Find all measurements", description = "Returns all measurements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Measurements found"),
            @ApiResponse(responseCode = "400", description = "Server error")
    })
    @GetMapping
    public ResponseEntity<List<Measurement>> findAll(){
        return ResponseEntity.ok(measurementService.getAll());
    }

    @Operation(summary = "Find rainy measurements", description = "Returns a count of all rainy measurements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rainy measurement count has been found"),
            @ApiResponse(responseCode = "400", description = "Server error")
    })
    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Integer> getRainingCount(){
        return ResponseEntity.ok(measurementService.getRainingCount());
    }
}
