package com.example.karting.controller;

import com.example.karting.dto.WinnerDetails;
import com.example.karting.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/race")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;

    @GetMapping("/start-from-file")
    public String startRaceFromFile() {
        try {
            raceService.startRaceFromFile();
            return "Race started from file!";
        } catch (IOException | URISyntaxException e) {
            return "Error reading from CSV file: " + e.getMessage();
        }
    }

    @GetMapping("/winner")
    public String getWinnerDetails2() {
        WinnerDetails winnerDetails = raceService.getWinnerDetails();

        if (winnerDetails == null) {
            return "No winner recorded.";
        }

        StringBuilder table = new StringBuilder();
        table.append("<table style=\"border-collapse: collapse; width: 50%; margin: 0 auto;\">");
        table.append("<tr><th style=\"border: 1px solid #000; text-align: center;\">Kart Number</th><th style=\"border: 1px solid #000; text-align: center;\">Lap Start Time</th><th style=\"border: 1px solid #000; text-align: center;\">Lap Number</th><th style=\"border: 1px solid #000; text-align: center;\">Fastest Lap Time</th></tr>");

        table.append("<tr>");
        table.append("<td style=\"border: 1px solid #000; text-align: center;\">").append(winnerDetails.getKartNumber()).append("</td>");
        table.append("<td style=\"border: 1px solid #000; text-align: center;\">").append(winnerDetails.getStartLapTime()).append("</td>");
        table.append("<td style=\"border: 1px solid #000; text-align: center;\">").append(winnerDetails.getLapNumber()).append("</td>");
        table.append("<td style=\"border: 1px solid #000; text-align: center;\">").append(winnerDetails.getFastestLap()).append("</td>");
        table.append("</tr>");

        table.append("</table>");

        return "<h1 style=\"text-align: center;\">Winner of the race!</h1>" + table.toString();
    }

}
