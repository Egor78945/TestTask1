package com.example.testtask1.repository;

import com.example.testtask1.model.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    @Query("SELECT COUNT(*) FROM Measurement WHERE raining=true")
    Integer findRainingCount();
}
