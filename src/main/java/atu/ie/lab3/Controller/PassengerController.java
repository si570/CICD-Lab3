package atu.ie.lab3.controller;

import atu.ie.lab3.model.Passenger;
import atu.ie.lab3.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        Optional<Passenger> passenger = passengerService.getPassengerById(id);

        return passenger.isPresent()
                ? ResponseEntity.ok(passenger.get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Passenger> addPassenger(@Valid @RequestBody Passenger passenger) {
        Passenger saved = passengerService.addPassenger(passenger);
        return ResponseEntity
                .created(URI.create("/api/passengers/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(
            @PathVariable Long id,
            @Valid @RequestBody Passenger passenger) {

        return passengerService.updatePassenger(id, passenger)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        return passengerService.deletePassenger(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
