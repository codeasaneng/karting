package com.example.karting.service;

import com.example.karting.dto.WinnerDetails;
import com.example.karting.model.LapTime;
import com.example.karting.repository.LapTimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;

import java.time.LocalTime;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RaceServiceTest {

    @Mock
    private LapTimeRepository lapTimeRepository;

    @Mock
    private Resource csvResource;

    @InjectMocks
    private RaceService raceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWinnerDetailsTest() {
        // Given
        LapTime lapTime = new LapTime();
        lapTime.setKartNumber(5);
        lapTime.setLapDuration("00:01:30");
        lapTime.setStartTime(LocalTime.of(12, 0, 0));
        lapTime.setLapNumber(1);

        when(lapTimeRepository.findFastestLap()).thenReturn(Arrays.asList(lapTime));

        // When
        WinnerDetails winnerDetails = raceService.getWinnerDetails();

        // Then
        assertEquals(5, winnerDetails.getKartNumber());
        assertEquals("00:01:30", winnerDetails.getFastestLap());
        assertEquals(LocalTime.of(12, 0, 0), winnerDetails.getStartLapTime());
        assertEquals(1, winnerDetails.getLapNumber());
    }
}
