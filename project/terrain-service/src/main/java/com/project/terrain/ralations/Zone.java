package com.project.terrain.ralations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.terrain.entities.Terrain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class Zone {
    private Long idZone;
    private String nomZone;
    private Ville ville;
}
