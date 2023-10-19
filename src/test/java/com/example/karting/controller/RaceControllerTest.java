package com.example.karting.controller;

import com.example.karting.dto.WinnerDetails;
import com.example.karting.service.RaceService;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalTime;
import java.io.IOException;


class RaceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RaceService raceService;

    @InjectMocks
    private RaceController raceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(raceController).build();
    }

    @Test
    void startRaceFromFileTest_success() throws Exception {
        // Given
        doNothing().when(raceService).startRaceFromFile();

        // When and Then
        mockMvc.perform(get("/race/start-from-file"))
            .andExpect(status().isOk())
            .andExpect(content().string("Race started from file!"));
    }

    @Test
    void startRaceFromFileTest_failure() throws Exception {
        // Given
        doThrow(new IOException("Test IOException")).when(raceService).startRaceFromFile();

        // When and Then
        mockMvc.perform(get("/race/start-from-file"))
            .andExpect(status().isOk())
            .andExpect(content().string("Error reading from CSV file: Test IOException"));
    }

    @Test
    void getWinnerDetails2Test() throws Exception {
        // Given
        WinnerDetails winnerDetails = new WinnerDetails();
        winnerDetails.setKartNumber(5);
        winnerDetails.setFastestLap("00:01:30");
        winnerDetails.setStartLapTime(LocalTime.of(12, 0, 0));
        winnerDetails.setLapNumber(1);

        when(raceService.getWinnerDetails()).thenReturn(winnerDetails);

        // When and Then
        mockMvc.perform(get("/race/winner"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("<h1 style=\"text-align: center;\">Winner of the race!</h1>"))); // Checking for a part of the result for brevity
    }

    @Test
    void getWinnerDetails2Test_noWinner() throws Exception {
        // Given
        when(raceService.getWinnerDetails()).thenReturn(null);

        // When and Then
        mockMvc.perform(get("/race/winner"))
            .andExpect(status().isOk())
            .andExpect(content().string("No winner recorded."));
    }
}
