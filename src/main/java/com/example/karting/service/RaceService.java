package com.example.karting.service;

import com.example.karting.dto.WinnerDetails;
import com.example.karting.model.LapTime;
import com.example.karting.repository.LapTimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class RaceService {

    private final  LapTimeRepository lapTimeRepository;

    @Value("classpath:races/karttimes.csv")
    private Resource csvResource;


    public void startRaceFromFile() throws IOException, URISyntaxException {
        log.info("Starting race processing from file...");

        Map<Integer, LocalTime> lastPassingTimes = new HashMap<>();
        Map<Integer, Integer> kartLapNumbers = new HashMap<>(); // Map to track lap number for each kart

        try (
            Reader reader = new BufferedReader(new InputStreamReader(csvResource.getInputStream()));
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)
        ) {
            for (CSVRecord record : csvParser) {
                int kartNumber = Integer.parseInt(record.get("kart"));
                LocalTime passingTime = LocalTime.parse(record.get("passingtime"));

                if (lastPassingTimes.containsKey(kartNumber)) {
                    LocalTime lastPassingTime = lastPassingTimes.get(kartNumber);
                    Duration lapDurationObj = Duration.between(lastPassingTime, passingTime);
                    String lapDuration = formatDuration(lapDurationObj);

                    // Get the current lap number for the kart, or initialize to 1 if it's a new lap
                    int lapNumber = kartLapNumbers.getOrDefault(kartNumber, 1);

                    LapTime lapTime = LapTime.builder()
                         .kartNumber(kartNumber)
                         .lapDuration(lapDuration)
                         .startTime(passingTime)
                         .lapNumber(lapNumber)
                         .build();
                    lapTimeRepository.save(lapTime);

                    // Increment lapNumber for the next lap
                    lapNumber++;
                    kartLapNumbers.put(kartNumber, lapNumber);
                }

                lastPassingTimes.put(kartNumber, passingTime);
            }
            log.info("Race processing completed successfully.");
        } catch (IOException e) {
            log.error("Error processing the race from the file: ", e);
            throw e;
        }       
    }


    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


    public WinnerDetails getWinnerDetails() {
        List<LapTime> fastestLaps = lapTimeRepository.findFastestLap();
        LapTime fastestLap = fastestLaps.get(0);


        if (fastestLap == null) {
            log.warn("No lap times recorded. Cannot determine winner.");
            throw new RuntimeException("No lap times recorded. Cannot determine winner.");
        }

        WinnerDetails winnerDetails = new WinnerDetails();
        winnerDetails.setKartNumber(fastestLap.getKartNumber());
        winnerDetails.setFastestLap(fastestLap.getLapDuration());
        winnerDetails.setStartLapTime(fastestLap.getStartTime());
        winnerDetails.setLapNumber(fastestLap.getLapNumber());

        log.info("Winner determined: Kart Number {}, Fastest Lap Duration {}, Started at {}, Lap Number {}",
                 winnerDetails.getKartNumber(), winnerDetails.getFastestLap(), 
                 winnerDetails.getStartLapTime(), winnerDetails.getLapNumber());

        return winnerDetails;
    }
}
