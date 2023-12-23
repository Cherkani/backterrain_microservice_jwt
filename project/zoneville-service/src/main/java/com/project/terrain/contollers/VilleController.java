package com.project.terrain.contollers;


import com.project.terrain.entities.Ville;
import com.project.terrain.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ville")
public class VilleController {


    @Autowired
    VilleService service;
    @GetMapping("oki")
    public String greetingMessage() {
        return "okioki";
    }
    @GetMapping
    public List<Ville> findAllVilles(){
        return service.findAll();
    }

    @PostMapping
    public Ville createVille(@RequestBody Ville Ville){
        Ville.setIdVille(Long.valueOf(0));
        return service.create(Ville);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Ville Ville=service.findById(id);
        if (Ville==null) {
            return new ResponseEntity<Object>("la Ville avec id : "+id+"est introuvable", HttpStatus.BAD_REQUEST);
        }else {
            return ResponseEntity.ok(Ville);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVille(@PathVariable int id,@RequestBody Ville newVille){
        Ville ville=service.findById(id);
        if (ville ==null) {
            return new ResponseEntity<Object>("la Ville avec id : "+id+"est introuvable", HttpStatus.BAD_REQUEST);
        }else {
            newVille.setIdVille(Long.valueOf(id));
            return ResponseEntity.ok(service.update(newVille));
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVille(@PathVariable int id) {
        Ville ville = service.findById(id);
        if (ville == null) {
            return new ResponseEntity<Object>("la Ville avec id : " + id + "est introuvable", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(service.delete(ville));
        }

    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	