package com.nihat.flightsearchapi.services;


import com.nihat.flightsearchapi.models.FlightDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightSearchService {
    List<FlightDTO> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime returnDateTime);
}

