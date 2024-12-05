package com.example.testtask1.repository;

import com.example.testtask1.model.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Boolean existsSensorByName(String name);
    Optional<Sensor> findSensorByName(String name);
}
