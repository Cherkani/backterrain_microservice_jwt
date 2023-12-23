package com.project.terrain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Photo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_photo")
    private long idPhoto;

    @Column(name = "url")
    private String urlPhoto;

    @ManyToOne
    @JoinColumn(name = "id_terrain")
    private Terrain terrain;
}
