package com.project.terrain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "Reservation")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private long idReservation;

    @Column(name = "date")
    private Date dateReservation;

    @Column(name = "etat")
    private String etatReservation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "reservation")
    private List<ReservationTerrain> reservations = new ArrayList<>();
}
