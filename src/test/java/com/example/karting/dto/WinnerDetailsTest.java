package com.example.karting.dto;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WinnerDetailsTest {

    @Test
    void defaultConstructorTest() {
        WinnerDetails winnerDetails = new WinnerDetails();
        assertNotNull(winnerDetails); 
        assertEquals(0, winnerDetails.getKartNumber());
        assertNull(winnerDetails.getFastestLap());
        assertNull(winnerDetails.getStartLapTime());
        assertEquals(0, winnerDetails.getLapNumber());
    }

    @Test
    void setterAndGetterTest() {
        WinnerDetails winnerDetails = new WinnerDetails();

        winnerDetails.setKartNumber(5);
        winnerDetails.setFastestLap("00:01:30");
        winnerDetails.setStartLapTime(LocalTime.of(12, 0, 0));
        winnerDetails.setLapNumber(1);

        assertEquals(5, winnerDetails.getKartNumber());
        assertEquals("00:01:30", winnerDetails.getFastestLap());
        assertEquals(LocalTime.of(12, 0, 0), winnerDetails.getStartLapTime());
        assertEquals(1, winnerDetails.getLapNumber());
    }
}
