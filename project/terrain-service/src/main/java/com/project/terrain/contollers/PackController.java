package com.project.terrain.contollers;

import com.project.terrain.entities.Pack;
import com.project.terrain.repositories.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Packs")
public class PackController {

    @Autowired
    private PackRepository tr;

    @GetMapping("/all")
    public ResponseEntity<List<Pack>> getAllPacks() {
        List<Pack> Packs = tr.findAll();
        return ResponseEntity.ok(Packs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pack> getPackById(@PathVariable Long id) {
        Optional<Pack> optionalPack = tr.findById(id);
        return optionalPack.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Pack Pack) {
        try {
            tr.save(Pack);
            return ResponseEntity.ok("Pack saved successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving Pack: " + ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Pack updatedPack) {
        try {
            Optional<Pack> optionalPack = tr.findById(id);
            if (optionalPack.isPresent()) {
                Pack existingPack = optionalPack.get();
                // Update fields of existingPack with the values from updatedPack
                // For example: existingPack.setName(updatedPack.getName());
                tr.save(existingPack);
                return ResponseEntity.ok("Pack updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Pack: " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(required = true) Long id) {
        try {
            tr.deleteById(id);
            return ResponseEntity.ok("Pack deleted successfully");
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting Pack: " + ex.getMessage());
        }
    }
}
