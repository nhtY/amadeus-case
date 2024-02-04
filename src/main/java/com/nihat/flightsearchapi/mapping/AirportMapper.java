package com.nihat.flightsearchapi.mapping;

import com.nihat.flightsearchapi.entities.Airport;
import com.nihat.flightsearchapi.models.AirportDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AirportMapper {

    AirportDTO toAirportDTO(Airport airport);

    Airport toAirport(AirportDTO airportDTO);
}
