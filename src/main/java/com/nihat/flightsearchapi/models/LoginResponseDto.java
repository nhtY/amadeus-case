package com.nihat.flightsearchapi.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String jwt;
}
