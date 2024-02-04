package com.nihat.flightsearchapi.validation.confirmpassword;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConfirmPasswordConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPasswordConstraint {

    String message() default "Password and Confirm Password do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

