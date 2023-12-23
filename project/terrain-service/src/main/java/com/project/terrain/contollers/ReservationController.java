package com.project.terrain.contollers;

import com.project.terrain.entities.Reservation;
import com.project.terrain.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository tr;

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> Reservations = tr.findAll();
        return ResponseEntity.ok(Reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> optionalReservation = tr.findById(id);
        return optionalReservation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Reservation Reservation) {
        try {
            tr.save(Reservation);
            return ResponseEntity.ok("Reservation saved successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving Reservation: " + ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        try {
            Optional<Reservation> optionalReservation = tr.findById(id);
            if (optionalReservation.isPresent()) {
                Reservation existingReservation = optionalReservation.get();
                // Update fields of existingReservation with the values from updatedReservation
                // For example: existingReservation.setName(updatedReservation.getName());
                tr.save(existingReservation);
                return ResponseEntity.ok("Reservation updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Reservation: " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(required = true) Long id) {
        try {
            tr.deleteById(id);
            return ResponseEntity.ok("Reservation deleted successfully");
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting Reservation: " + ex.getMessage());
        }
    }
}
