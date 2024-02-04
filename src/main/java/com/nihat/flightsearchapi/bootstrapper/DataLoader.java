package com.nihat.flightsearchapi.bootstrapper;

import com.nihat.flightsearchapi.entities.Airport;
import com.nihat.flightsearchapi.entities.Flight;
import com.nihat.flightsearchapi.repositories.AirportRepository;
import com.nihat.flightsearchapi.repositories.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public DataLoader(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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
}
