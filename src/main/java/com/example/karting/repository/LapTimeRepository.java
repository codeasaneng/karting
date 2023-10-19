package com.example.karting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.karting.model.LapTime;

import java.util.List;

public interface LapTimeRepository extends JpaRepository<LapTime, Long> {
    
    @Query("SELECT l FROM LapTime l WHERE l.lapDuration = (SELECT MIN(l2.lapDuration) FROM LapTime l2)")
    List<LapTime> findFastestLap();
}
