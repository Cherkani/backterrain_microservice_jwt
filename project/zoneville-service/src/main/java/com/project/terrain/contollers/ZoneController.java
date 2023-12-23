package com.project.terrain.contollers;

import com.project.terrain.entities.Zone;
import com.project.terrain.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zone")
public class ZoneController {


    @Autowired
    ZoneService service;
    @GetMapping
    public List<Zone> findAllZones(){
        return service.findAll();
    }

    @PostMapping
    public Zone createZone(@RequestBody Zone Zone){
        Zone.setIdZone(Long.valueOf(0));
        return service.create(Zone);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Zone Zone=service.findById(id);
        if (Zone==null) {
            return new ResponseEntity<Object>("la Zone avec id : "+id+"est introuvable", HttpStatus.BAD_REQUEST);
        }else {
            return ResponseEntity.ok(Zone);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateZone(@PathVariable int id,@RequestBody Zone newZone){
        Zone Zone=service.findById(id);
        if (Zone ==null) {
            return new ResponseEntity<Object>("la Zone avec id : "+id+"est introuvable", HttpStatus.BAD_REQUEST);
        }else {
            newZone.setIdZone(Long.valueOf(id));
            return ResponseEntity.ok(service.update(newZone));
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteZone(@PathVariable int id) {
        Zone Zone = service.findById(id);
        if (Zone == null) {
            return new ResponseEntity<Object>("la Zone avec id : " + id + "est introuvable", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(service.delete(Zone));
        }

    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	