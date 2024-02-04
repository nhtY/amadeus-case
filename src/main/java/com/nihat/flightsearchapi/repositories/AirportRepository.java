package com.nihat.flightsearchapi.repositories;

import com.nihat.flightsearchapi.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
