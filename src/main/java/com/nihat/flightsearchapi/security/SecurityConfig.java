package com.nihat.flightsearchapi.security;

import com.nihat.flightsearchapi.repositories.UserRepository;
import com.nihat.flightsearchapi.security.jwt.JwtTokenFilterConfigurer;
import com.nihat.flightsearchapi.security.jwt.JwtTokenProvider;
import com.nihat.flightsearchapi.services.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final AuthenticationConfiguration authConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/flights/search/**").permitAll()
                        .anyRequest().authenticated())
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/api/**", "/h2-console/**"))
                .headers(configure -> configure.frameOptions( // TODO DELETE THIS ONE - INSECURE
                        frameOptionsConfig -> frameOptionsConfig.disable()
                ))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .with(new JwtTokenFilterConfigurer(jwtTokenProvider), Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public CustomUserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

}
