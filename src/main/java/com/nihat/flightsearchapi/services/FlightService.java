package com.nihat.flightsearchapi.services;

import com.nihat.flightsearchapi.models.FlightDTO;
import com.nihat.flightsearchapi.models.FlightUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface FlightService {

    List<FlightDTO> getAll();

    FlightDTO save(FlightDTO flightDTO);

    Optional<FlightDTO> getById(Long id);

    void deleteById(Long id);

    Optional<FlightDTO> update(Long id, FlightUpdateDTO flightUpdateDTO);
}
