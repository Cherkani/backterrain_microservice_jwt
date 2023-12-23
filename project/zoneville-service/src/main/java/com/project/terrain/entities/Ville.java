package com.project.terrain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Ville")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ville")
    private Long idVille;

    @Column(name = "ville")
    private String nomVille;



    @OneToMany(mappedBy = "ville")
    @JsonIgnore
    private List<Zone> zones=new ArrayList<>();
}
