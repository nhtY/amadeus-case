package com.nihat.flightsearchapi.repositories;

import com.nihat.flightsearchapi.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
