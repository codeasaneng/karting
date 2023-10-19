package com.example.karting.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class LapTimeTest {

    @Test
    void testGettersAndSetters() {
        LapTime lapTime = new LapTime();
        lapTime.setId(1L);
        lapTime.setKartNumber(123);
        lapTime.setLapDuration("1:23:45");
        lapTime.setStartTime(LocalTime.of(14, 30, 0));
        lapTime.setLapNumber(5);

        assertEquals(1L, lapTime.getId());
        assertEquals(123, lapTime.getKartNumber());
        assertEquals("1:23:45", lapTime.getLapDuration());
        assertEquals(LocalTime.of(14, 30, 0), lapTime.getStartTime());
        assertEquals(5, lapTime.getLapNumber());
    }

    @Test
    void testBuilder() {
        LapTime lapTime = LapTime.builder()
                .id(1L)
                .kartNumber(123)
                .lapDuration("1:23:45")
                .startTime(LocalTime.of(14, 30, 0))
                .lapNumber(5)
                .build();

        assertEquals(1L, lapTime.getId());
        assertEquals(123, lapTime.getKartNumber());
        assertEquals("1:23:45", lapTime.getLapDuration());
        assertEquals(LocalTime.of(14, 30, 0), lapTime.getStartTime());
        assertEquals(5, lapTime.getLapNumber());
    }

    @Test
    void testEqualsAndHashCode() {
        LapTime lapTime1 = LapTime.builder().id(1L).build();
        LapTime lapTime2 = LapTime.builder().id(1L).build();

        assertEquals(lapTime1, lapTime2);
        assertEquals(lapTime1.hashCode(), lapTime2.hashCode());
    }
}
