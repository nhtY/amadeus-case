package com.nihat.flightsearchapi.mapping;

import com.nihat.flightsearchapi.entities.Flight;
import com.nihat.flightsearchapi.models.FlightDTO;
import org.mapstruct.Mapper;

@Mapper
public interface FlightMapper {

     FlightDTO toFlightDTO(Flight flight);

     Flight toFlight(FlightDTO flightDTO);
}
