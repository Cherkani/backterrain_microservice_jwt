package com.project.terrain.contollers;

import com.project.terrain.entities.ReservationTerrain;
import com.project.terrain.repositories.ReservationTerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ReservationTerrains")
public class ReservationTerrainController {

    @Autowired
    private ReservationTerrainRepository tr;

    @GetMapping("/all")
    public ResponseEntity<List<ReservationTerrain>> getAllReservationTerrains() {
        List<ReservationTerrain> ReservationTerrains = tr.findAll();
        return ResponseEntity.ok(ReservationTerrains);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationTerrain> getReservationTerrainById(@PathVariable Long id) {
        Optional<ReservationTerrain> optionalReservationTerrain = tr.findById(id);
        return optionalReservationTerrain.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ReservationTerrain ReservationTerrain) {
        try {
            tr.save(ReservationTerrain);
            return ResponseEntity.ok("ReservationTerrain saved successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving ReservationTerrain: " + ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ReservationTerrain updatedReservationTerrain) {
        try {
            Optional<ReservationTerrain> optionalReservationTerrain = tr.findById(id);
            if (optionalReservationTerrain.isPresent()) {
                ReservationTerrain existingReservationTerrain = optionalReservationTerrain.get();
                // Update fields of existingReservationTerrain with the values from updatedReservationTerrain
                // For example: existingReservationTerrain.setName(updatedReservationTerrain.getName());
                tr.save(existingReservationTerrain);
                return ResponseEntity.ok("ReservationTerrain updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating ReservationTerrain: " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(required = true) Long id) {
        try {
            tr.deleteById(id);
            return ResponseEntity.ok("ReservationTerrain deleted successfully");
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting ReservationTerrain: " + ex.getMessage());
        }
    }
}
