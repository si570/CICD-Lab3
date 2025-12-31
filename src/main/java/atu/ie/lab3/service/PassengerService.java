package atu.ie.lab3.service;

import ie.atu.passenger.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private final List<Passenger> passengers = new ArrayList<>();

    public List<Passenger> getAllPassengers() {
        return passengers;
    }

    public Optional<Passenger> getPassengerById(Long id) {
        return passengers.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Passenger addPassenger(Passenger passenger) {
        passengers.add(passenger);
        return passenger;
    }

    public Optional<Passenger> updatePassenger(Long id, Passenger updatedPassenger) {
        Optional<Passenger> existing = getPassengerById(id);

        if (existing.isPresent()) {
            Passenger passenger = existing.get();
            passenger.setName(updatedPassenger.getName());
            passenger.setEmail(updatedPassenger.getEmail());
            return Optional.of(passenger);
        }

        return Optional.empty();
    }

    public boolean deletePassenger(Long id) {
        return passengers.removeIf(p -> p.getId().equals(id));
    }
}

