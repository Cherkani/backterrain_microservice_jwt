package com.project.terrain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Club")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_club")
    private long idClub;

    @Column(name = "nom")
    private String nomClub;

    @JsonIgnore
    @OneToMany(mappedBy = "club")
    private List<Pack> packs=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "club")
    private List<Terrain> terrains=new ArrayList<>();
}