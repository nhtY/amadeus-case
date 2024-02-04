package com.nihat.flightsearchapi.repositories;

import com.nihat.flightsearchapi.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeAfterAndReturnDateTimeIsNull(
            String departureCity, String arrivalCity, LocalDateTime departureDateTime);

    List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeAfterAndReturnDateTimeNotNull(
            String departureCity, String arrivalCity, LocalDateTime departureDateTime);

}
