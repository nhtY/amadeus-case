package com.nihat.flightsearchapi.repositories;

import com.nihat.flightsearchapi.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByCity(String departureCity);
}
