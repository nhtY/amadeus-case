package com.nihat.flightsearchapi.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportDTO {
    private Long id;

    @NotNull
    @NotBlank
    @Min(3)
    private String city;
}
