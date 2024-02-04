package com.nihat.flightsearchapi.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class FlightDTO {

    private Long id;

    @NotNull
    private AirportDTO departureAirport;

    @NotNull
    private AirportDTO arrivalAirport;

    @NotNull
    private LocalDateTime departureDateTime;

    private LocalDateTime returnDateTime;

    @NotNull
    private BigDecimal price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
