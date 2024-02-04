package com.nihat.flightsearchapi.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class FlightUpdateDTO {

    @NotNull
    private Long id;

    private Long departureAirportId;

    private Long arrivalAirportId;

    private LocalDateTime departureDateTime;

    private LocalDateTime returnDateTime;

    private BigDecimal price;
}
