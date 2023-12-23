package com.project.terrain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name = "Reservation_terrain")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationTerrain {
    @EmbeddedId
    private ReservationTerrainKey idTerrainReservation;

    @MapsId("terrain")
    @JoinColumn(name = "terrain", referencedColumnName = "id_terrain", insertable = false, updatable = false)
    @ManyToOne
    private Terrain terrain;

    @MapsId("reservation")
    @JoinColumn(name = "reservation", referencedColumnName = "id_reservation", insertable = false, updatable = false)
    @ManyToOne
    private Reservation reservation;

    @Column(name = "date")
    private Date dateReservationterrain;

    @Column(name = "heure")
    private String heureReservationterrain;
}