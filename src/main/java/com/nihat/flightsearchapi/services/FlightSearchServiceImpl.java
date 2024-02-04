package com.nihat.flightsearchapi.services;

import com.nihat.flightsearchapi.mapping.FlightMapper;
import com.nihat.flightsearchapi.models.FlightDTO;
import com.nihat.flightsearchapi.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements FlightSearchService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;


    @Override
    public List<FlightDTO> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        if (returnDateTime == null) {
            // Tek yönlü uçuş
            return flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeAfterAndReturnDateTimeIsNull(
                    departureCity, arrivalCity, departureDateTime).stream()
                    .map(flightMapper::toFlightDTO)
                    .collect(Collectors.toList());
        } else {
            // Çift yönlü uçuş
            return flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeAfterAndReturnDateTimeNotNull(
                    departureCity, arrivalCity, departureDateTime).stream()
                    .map(flightMapper::toFlightDTO)
                    .collect(Collectors.toList());
        }
    }
}
