package com.example.karting.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class WinnerDetails {
    private int kartNumber;
    private String fastestLap;
    private LocalTime startLapTime;
    private int lapNumber;
}