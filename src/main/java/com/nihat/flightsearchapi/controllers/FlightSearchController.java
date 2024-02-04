package com.nihat.flightsearchapi.controllers;

import com.nihat.flightsearchapi.models.FlightDTO;
import com.nihat.flightsearchapi.services.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @Autowired
    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDTO>> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime departureDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime returnDateTime) {

        List<FlightDTO> flights = flightSearchService.searchFlights(departureCity, arrivalCity, departureDateTime, returnDateTime);

        return ResponseEntity.ok(flights);
    }
}
