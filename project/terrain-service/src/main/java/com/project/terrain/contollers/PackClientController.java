package com.project.terrain.contollers;

import com.project.terrain.entities.PackClient;
import com.project.terrain.repositories.PackClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/PackClients")
public class PackClientController {

    @Autowired
    private PackClientRepository tr;

    @GetMapping("/all")
    public ResponseEntity<List<PackClient>> getAllPackClients() {
        List<PackClient> PackClients = tr.findAll();
        return ResponseEntity.ok(PackClients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackClient> getPackClientById(@PathVariable Long id) {
        Optional<PackClient> optionalPackClient = tr.findById(id);
        return optionalPackClient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PackClient PackClient) {
        try {
            tr.save(PackClient);
            return ResponseEntity.ok("PackClient saved successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving PackClient: " + ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PackClient updatedPackClient) {
        try {
            Optional<PackClient> optionalPackClient = tr.findById(id);
            if (optionalPackClient.isPresent()) {
                PackClient existingPackClient = optionalPackClient.get();
                // Update fields of existingPackClient with the values from updatedPackClient
                // For example: existingPackClient.setName(updatedPackClient.getName());
                tr.save(existingPackClient);
                return ResponseEntity.ok("PackClient updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating PackClient: " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(required = true) Long id) {
        try {
            tr.deleteById(id);
            return ResponseEntity.ok("PackClient deleted successfully");
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting PackClient: " + ex.getMessage());
        }
    }
}
