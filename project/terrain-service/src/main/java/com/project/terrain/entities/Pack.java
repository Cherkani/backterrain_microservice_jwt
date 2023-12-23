package com.project.terrain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Pack")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pack")
    private long idPack;

    @Column(name="nom")
    private String nomPack;

    @Column(name = "tarif")
    private double tarifPack;

    @Column(name="nbrDeMatches")
    private int nbrDeMatchesPack;

    @OneToMany(mappedBy = "pack")
    private List<PackClient> packclients = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_club")
    private Club club;
}
