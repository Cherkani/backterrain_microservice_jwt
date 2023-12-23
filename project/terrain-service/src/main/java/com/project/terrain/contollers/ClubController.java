package com.project.terrain.contollers;

import com.project.terrain.entities.Club;
import com.project.terrain.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Clubs")
public class ClubController {

    @Autowired
    private ClubRepository tr;

    @GetMapping("/all")
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> Clubs = tr.findAll();
        return ResponseEntity.ok(Clubs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Optional<Club> optionalClub = tr.findById(id);
        return optionalClub.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Club Club) {
        try {
            tr.save(Club);
            return ResponseEntity.ok("Club saved successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving Club: " + ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Club updatedClub) {
        try {
            Optional<Club> optionalClub = tr.findById(id);
            if (optionalClub.isPresent()) {
                Club existingClub = optionalClub.get();
                // Update fields of existingClub with the values from updatedClub
                // For example: existingClub.setName(updatedClub.getName());
                tr.save(existingClub);
                return ResponseEntity.ok("Club updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Club: " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(required = true) Long id) {
        try {
            tr.deleteById(id);
            return ResponseEntity.ok("Club deleted successfully");
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting Club: " + ex.getMessage());
        }
    }
}
