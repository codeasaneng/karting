package com.example.karting.repository;

import com.example.karting.model.LapTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class LapTimeRepositoryTest {

    @Autowired
    private LapTimeRepository lapTimeRepository;

    @Test
    void findFastestLapTest() {
        // Given
        LapTime lap1 = new LapTime();
        lap1.setLapDuration("00:01:20");

        LapTime lap2 = new LapTime();
        lap2.setLapDuration("00:01:10");

        LapTime lap3 = new LapTime();
        lap3.setLapDuration("00:01:30");

        lapTimeRepository.save(lap1);
        lapTimeRepository.save(lap2);
        lapTimeRepository.save(lap3);

        // When
        List<LapTime> result = lapTimeRepository.findFastestLap();

        // Then
        assertEquals(1, result.size());
        assertEquals("00:01:10", result.get(0).getLapDuration());
    }
}
