package com.nihat.flightsearchapi.scheduled;

import com.nihat.flightsearchapi.entities.Airport;
import com.nihat.flightsearchapi.entities.Flight;
import com.nihat.flightsearchapi.repositories.AirportRepository;
import com.nihat.flightsearchapi.repositories.FlightRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class FlightDataScheduledJob {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public FlightDataScheduledJob(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // This will run the job every day at midnight
    public void fetchAndSaveFlightData() {
        String apiUrl = "https://mock-api.com/flights";
        RestTemplate restTemplate = new RestTemplate();
        String mockApiResponse = restTemplate.getForObject(apiUrl, String.class);


        String mockFlightData = "{\"departure\": \"London\", \"arrival\": \"Istanbul\", \"price\": 150.0}";
        String mockFlightData2 = "{\"departure\": \"Paris\", \"arrival\": \"New York\", \"price\": 300.0}";

        // Save the parsed data to the database
        saveFlightDataToDatabase(mockFlightData);
        saveFlightDataToDatabase(mockFlightData2);
    }

    private void saveFlightDataToDatabase(String apiResponse) {
        String departureCity = "London"; // Extracted from API response
        String arrivalCity = "Istanbul"; // Extracted from API response
        BigDecimal price = BigDecimal.valueOf(150.0); // Extracted from API response

        // Assume airports already exist in the database, retrieve them
        Airport departureAirport = airportRepository.findByCity(departureCity).orElse(null);
        Airport arrivalAirport = airportRepository.findByCity(arrivalCity).orElse(null);

        // Create and save a new flight entity
        Flight newFlight = Flight.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDateTime(LocalDateTime.now())
                .price(price)
                .build();

        flightRepository.save(newFlight);
    }
}
