package com.nihat.flightsearchapi.models;

import com.nihat.flightsearchapi.validation.confirmpassword.ConfirmPasswordConstraint;
import com.nihat.flightsearchapi.validation.password.PasswordConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ConfirmPasswordConstraint
public class RegisterRequestDto {

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    @PasswordConstraint
    @Size(min = 6, max = 36)
    private String password;

    private String confirmPassword;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 20)
    private String surname;
}
