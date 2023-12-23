package com.project.terrain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private long idClient;

    @Column(name = "nom")
    private String nomClient;

    @Column(name = "prenom")
    private String prenomClient;

    @Column(name = "email")
    private String emailClient;

    @Column(name = "password")
    private String passwordClient;

    @OneToMany(mappedBy = "client")
    private List<PackClient> packclients = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations = new ArrayList<>();
}