package com.project.terrain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Zone")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zone")
    private Long idZone;

    @Column(name = "nom")
    private String nomZone;

//    @OneToMany(mappedBy = "zone")
//    private List<Terrain> terrains=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_ville")
    private Ville ville;



}
