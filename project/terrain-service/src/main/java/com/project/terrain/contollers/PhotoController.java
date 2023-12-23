package com.project.terrain.contollers;

import com.project.terrain.entities.Photo;
import com.project.terrain.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Photos")
public class PhotoController {

    @Autowired
    private PhotoRepository tr;

    @GetMapping("/all")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> Photos = tr.findAll();
        return ResponseEntity.ok(Photos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable Long id) {
        Optional<Photo> optionalPhoto = tr.findById(id);
        return optionalPhoto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Photo Photo) {
        try {
            tr.save(Photo);
            return ResponseEntity.ok("Photo saved successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving Photo: " + ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Photo updatedPhoto) {
        try {
            Optional<Photo> optionalPhoto = tr.findById(id);
            if (optionalPhoto.isPresent()) {
                Photo existingPhoto = optionalPhoto.get();
                // Update fields of existingPhoto with the values from updatedPhoto
                // For example: existingPhoto.setName(updatedPhoto.getName());
                tr.save(existingPhoto);
                return ResponseEntity.ok("Photo updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Photo: " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(required = true) Long id) {
        try {
            tr.deleteById(id);
            return ResponseEntity.ok("Photo deleted successfully");
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting Photo: " + ex.getMessage());
        }
    }
}
