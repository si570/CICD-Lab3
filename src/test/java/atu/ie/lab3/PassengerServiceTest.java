package atu.ie.lab3.service;

import atu.ie.lab3.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PassengerServiceTest {

    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        passengerService = new PassengerService();
    }

    @Test
    void addPassenger_success() {
        Passenger passenger = Passenger.builder()
                .id(1L)
                .name("Ali")
                .email("ali@test.com")
                .build();

        passengerService.addPassenger(passenger);

        assertEquals(1, passengerService.getAllPassengers().size());
    }

    @Test
    void getPassengerById_found() {
        passengerService.addPassenger(
                Passenger.builder().id(1L).name("Ali").email("a@test.com").build()
        );

        Optional<Passenger> result = passengerService.getPassengerById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void updatePassenger_success() {
        passengerService.addPassenger(
                Passenger.builder().id(1L).name("Ali").email("a@test.com").build()
        );

        Passenger update = Passenger.builder()
                .name("Updated")
                .email("updated@test.com")
                .build();

        Optional<Passenger> result = passengerService.updatePassenger(1L, update);

        assertTrue(result.isPresent());
        assertEquals("Updated", result.get().getName());
    }

    @Test
    void deletePassenger_success() {
        passengerService.addPassenger(
                Passenger.builder().id(1L).name("Ali").email("a@test.com").build()
        );

        assertTrue(passengerService.deletePassenger(1L));
    }
}
