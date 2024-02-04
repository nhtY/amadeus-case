package com.nihat.flightsearchapi.services;

import com.nihat.flightsearchapi.mapping.FlightMapper;
import com.nihat.flightsearchapi.models.FlightDTO;
import com.nihat.flightsearchapi.models.FlightUpdateDTO;
import com.nihat.flightsearchapi.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService{

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public List<FlightDTO> getAll() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toFlightDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FlightDTO save(FlightDTO flightDTO) {
        return flightMapper.toFlightDTO(flightRepository.save(flightMapper.toFlight(flightDTO)));
    }

    @Override
    public Optional<FlightDTO> getById(Long id) {
        return Optional.ofNullable(flightMapper.toFlightDTO(flightRepository.findById(id).orElse(null)));
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Optional<FlightDTO> update(Long id, FlightUpdateDTO flightUpdateDTO) {
        // find if the flight exists then update it
        return flightRepository.findById(id)
                .map(flight -> {
                    flight.setDepartureDateTime(flightUpdateDTO.getDepartureDateTime());

                    if (flightUpdateDTO.getReturnDateTime() != null) {
                        flight.setReturnDateTime(flightUpdateDTO.getReturnDateTime());
                    }

                    flight.setPrice(flightUpdateDTO.getPrice());
                    return flightMapper.toFlightDTO(flightRepository.save(flight));
                });
    }
}
