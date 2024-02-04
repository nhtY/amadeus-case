package com.nihat.flightsearchapi.services;

import com.nihat.flightsearchapi.models.AirportDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface AirportService {

    List<AirportDTO> getAll();

    @Transactional
    AirportDTO save(AirportDTO airportDTO);

    Optional<AirportDTO> getById(Long id);

    @Transactional
    void deleteById(Long id);

    @Transactional
    Optional<AirportDTO> update(Long id, AirportDTO airportDTO);

}
