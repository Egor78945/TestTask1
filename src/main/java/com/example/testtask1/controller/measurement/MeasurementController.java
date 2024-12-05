package com.example.testtask1.controller.measurement;

import com.example.testtask1.model.dto.MeasurementModel;
import com.example.testtask1.model.entity.Measurement;
import com.example.testtask1.service.measurement.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementService measurementService;

    @PostMapping("/add")
    public ResponseEntity<String> registerMeasurement(@RequestBody MeasurementModel measurementModel){
        measurementService.registerMeasurement(measurementModel);
        return ResponseEntity.ok("Measurement registered.");
    }

    @GetMapping
    public ResponseEntity<List<Measurement>> findAll(){
        return ResponseEntity.ok(measurementService.getAll());
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Integer> getRainingCount(){
        return ResponseEntity.ok(measurementService.getRainingCount());
    }
}
