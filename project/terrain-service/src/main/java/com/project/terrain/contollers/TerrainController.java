package com.project.terrain.contollers;


import com.project.terrain.TerrainServiceApplication;
import com.project.terrain.ZoneRepository;
import com.project.terrain.entities.Terrain;
import com.project.terrain.ralations.Zone;
import com.project.terrain.services.TerrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/terrain")
public class TerrainController {

    @Autowired
    TerrainService terrainService;

    @Autowired
    ZoneRepository zoneService;

    @GetMapping("/{id}")
    public ResponseEntity<Terrain> findById(@PathVariable Long id) {
        Terrain terrain = terrainService.findById(id);

        if (terrain == null) {
            return ResponseEntity.notFound().build();
        } else {
            Zone zone = zoneService.zoneById(terrain.getId_zone());
            terrain.setZone(zone);

            return ResponseEntity.ok(terrain);
        }
    }

    @GetMapping
    public List<Terrain> findAllTerrains() {
        List<Terrain> terrainList = terrainService.findAll();

        for (Terrain terrain : terrainList) {
            Zone zone = zoneService.zoneById(terrain.getId_zone());
            terrain.setZone(zone);
        }

        return terrainList;
    }

    @PostMapping
    public ResponseEntity<Object> createTerrain(@RequestBody Terrain terrain) {
        Zone zone = zoneService.zoneById(terrain.getId_zone());
        if (zone == null) {
            return new ResponseEntity<Object>("Zone with id: " + terrain.getId_zone() + " does not exist", HttpStatus.BAD_REQUEST);
        }

        terrain.setZone(zone);

        terrain.setIdTerrain(Long.valueOf(0));

        Terrain createdTerrain = terrainService.create(terrain);

        return ResponseEntity.ok(createdTerrain);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTerrain(@PathVariable int id, @RequestBody Terrain newTerrain) {
        Zone zone = zoneService.zoneById(newTerrain.getId_zone());
        if (zone == null) {
            return new ResponseEntity<Object>("Zone with id: " + newTerrain.getId_zone() + " does not exist", HttpStatus.BAD_REQUEST);
        }

        Terrain existingTerrain = terrainService.findById((long) id);
        if (existingTerrain == null) {
            return new ResponseEntity<Object>("Terrain with id: " + id + " not found", HttpStatus.BAD_REQUEST);
        }

        newTerrain.setZone(zone);

        newTerrain.setIdTerrain((long) id);

        Terrain updatedTerrain = terrainService.update(newTerrain);
        Zone zoness = zoneService.zoneById(newTerrain.getId_zone());
        updatedTerrain.setZone(zoness);

        return ResponseEntity.ok(updatedTerrain);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTerrain(@PathVariable int id) {
        Terrain terrain = terrainService.findById((long) id);
        terrain.setZone(null);

        if (terrain == null) {
            return new ResponseEntity<Object>("la Terrain avec id : " + id + "est introuvable", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(terrainService.delete(terrain));
    }

}


}
