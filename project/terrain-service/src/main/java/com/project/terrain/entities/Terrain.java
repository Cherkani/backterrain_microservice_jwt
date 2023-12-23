package com.project.terrain.entities;

import com.project.terrain.ralations.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Terrain")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Terrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_terrain")
    private Long idTerrain;

    @Column(name = "nom")
    private String nomTerrain;

    @Column(name="adresse")
    private String adresseTerrain;

    @Column(name="latitude")
    private double latitudeTerrain;

    @Column(name="longitude")
    private double longitudeTerrain;

    @Column(name="rank")
    private double rankTerrain;

    @Column(name="type")
    private String typeTerrain;

    @Column(name="etat")
    private String etatTerrain;

    @Column(name="description")
    private String descriptionTerrain;

    @Column(name="typeSal")
    private String typeSalTerrain;

    @Column(name="tarif")
    private double tarifTerrain;


    @OneToMany(mappedBy = "terrain")
    private List<Photo> photos=new ArrayList<>();


    @OneToMany(mappedBy = "terrain")  // This should match the field name in the ReservationTerrain class
    private List<ReservationTerrain> reservations = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name="id_club")
    private Club club;

    private Long id_zone;





    @Transient
    private Zone zone;


}