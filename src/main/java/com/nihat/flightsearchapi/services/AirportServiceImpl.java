package com.nihat.flightsearchapi.services;

import com.nihat.flightsearchapi.mapping.AirportMapper;
import com.nihat.flightsearchapi.models.AirportDTO;
import com.nihat.flightsearchapi.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService{

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public List<AirportDTO> getAll() {
        return airportRepository.findAll().stream()
                .map(airportMapper::toAirportDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AirportDTO save(AirportDTO dto) {
        return airportMapper.toAirportDTO(airportRepository.save(airportMapper.toAirport(dto)));
    }

    @Override
    public Optional<AirportDTO> getById(Long id) {
        return Optional.ofNullable(airportMapper.toAirportDTO(airportRepository.findById(id).orElse(null)));
    }

    @Override
    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }

    @Override
    public Optional<AirportDTO> update(Long id, AirportDTO dto) {

        return airportRepository.findById(id)
                .map(airport -> {
                    airport.setCity(dto.getCity());
                    return airportMapper.toAirportDTO(airportRepository.save(airport));
                });
    }

}
