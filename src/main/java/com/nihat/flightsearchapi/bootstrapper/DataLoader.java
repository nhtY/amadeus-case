package com.nihat.flightsearchapi.bootstrapper;

import com.nihat.flightsearchapi.entities.*;
import com.nihat.flightsearchapi.repositories.AirportRepository;
import com.nihat.flightsearchapi.repositories.FlightRepository;
import com.nihat.flightsearchapi.repositories.UserRepository;
import com.nihat.flightsearchapi.repositories.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    public DataLoader(AirportRepository airportRepository, FlightRepository flightRepository, UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {


        // load Roles and Users
        loadRolesAndUsers();


        // Create and save airports
        Airport london = Airport.builder().city("London").build();
        Airport istanbul = Airport.builder().city("Istanbul").build();
        Airport paris = Airport.builder().city("Paris").build();

        airportRepository.save(london);
        airportRepository.save(istanbul);
        airportRepository.save(paris);

        // Create and save flights
        Flight londonToIstanbul = Flight.builder()
                .departureAirport(london)
                .arrivalAirport(istanbul)
                .departureDateTime(LocalDateTime.now().plusDays(1))
                .price(BigDecimal.valueOf(100))
                .build();
        Flight londonToParis = Flight.builder()
                .departureAirport(london)
                .arrivalAirport(paris)
                .departureDateTime(LocalDateTime.now().plusDays(1))
                .price(BigDecimal.valueOf(150))
                .build();
        Flight istanbulToLondon = Flight.builder()
                .departureAirport(istanbul)
                .arrivalAirport(london)
                .departureDateTime(LocalDateTime.now().plusDays(1))
                .price(BigDecimal.valueOf(120))
                .build();

        Flight istanbulToLondon2 = Flight.builder()
                .departureAirport(istanbul)
                .arrivalAirport(london)
                .departureDateTime(LocalDateTime.now().plusDays(2))
                .price(BigDecimal.valueOf(150))
                .build();


        // Save single-way flights
        flightRepository.save(londonToIstanbul);
        flightRepository.save(londonToParis);
        flightRepository.save(istanbulToLondon);

        // Save two-way flights
        flightRepository.save(Flight.builder()
                .departureAirport(london)
                .arrivalAirport(istanbul)
                .departureDateTime(LocalDateTime.now().plusDays(1))
                .returnDateTime(LocalDateTime.now().plusDays(2))
                .price(BigDecimal.valueOf(200))
                .build());

        flightRepository.save(Flight.builder()
                .departureAirport(london)
                .arrivalAirport(istanbul)
                .departureDateTime(LocalDateTime.now().plusDays(1))
                .returnDateTime(LocalDateTime.now().plusDays(2))
                .price(BigDecimal.valueOf(250))
                .build());

        flightRepository.save(Flight.builder()
                .departureAirport(london)
                .arrivalAirport(istanbul)
                .departureDateTime(LocalDateTime.now().plusDays(1))
                .returnDateTime(LocalDateTime.now().plusDays(3))
                .price(BigDecimal.valueOf(300))
                .build());

        flightRepository.save(Flight.builder()
                .departureAirport(istanbul)
                .arrivalAirport(paris)
                .departureDateTime(LocalDateTime.now().plusDays(1))
                .returnDateTime(LocalDateTime.now().plusDays(2))
                .price(BigDecimal.valueOf(300))
                .build());

        flightRepository.save(Flight.builder()
                .departureAirport(istanbul)
                .arrivalAirport(paris)
                .departureDateTime(LocalDateTime.now().plusDays(1))
                .returnDateTime(LocalDateTime.now().plusDays(2))
                .price(BigDecimal.valueOf(200))
                .build());

        flightRepository.save(Flight.builder()
                .departureAirport(istanbul)
                .arrivalAirport(paris)
                .departureDateTime(LocalDateTime.now().plusDays(1))
                .returnDateTime(LocalDateTime.now().plusDays(3))
                .price(BigDecimal.valueOf(350))
                .build());

        System.out.println("Data loaded successfully.");
    }

    private void loadRolesAndUsers() {

        if (userRepository.count() == 0 && userRoleRepository.count() == 0) {
            // Add user and admin roles to the database
            UserRole userRole = UserRole.builder().role(Role.USER).build();
            UserRole adminRole = UserRole.builder().role(Role.ADMIN).build();

            userRoleRepository.save(userRole);
            userRoleRepository.save(adminRole);

            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(userRole);

            Set<UserRole> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            // Add one admin and one user to the AppUser table
            AppUser adminUser = AppUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin.A1"))
                    .email("admin@example.com")
                    .name("Admin")
                    .surname("User")
                    .userRoles(adminRoles)
                    .build();

            AppUser normalUser = AppUser.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user.U1"))
                    .email("user@example.com")
                    .name("Normal")
                    .surname("User")
                    .userRoles(userRoles)
                    .build();

            userRepository.save(adminUser);
            userRepository.save(normalUser);
        }

    }

}
