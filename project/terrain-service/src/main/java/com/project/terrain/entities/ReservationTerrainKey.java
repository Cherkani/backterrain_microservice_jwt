package com.project.terrain.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationTerrainKey implements Serializable {
    private long idReservationterrain;
    private long reservation;
    private long terrain;
}
