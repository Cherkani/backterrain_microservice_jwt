package com.project.terrain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "packclient")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackClient {
    @EmbeddedId
    private PackClientKey idPackClient;

    @JoinColumn(name = "pack", referencedColumnName = "id_pack",insertable=false, updatable=false)
    @ManyToOne
    private Pack pack;

    @JoinColumn(name = "client", referencedColumnName = "id_client",insertable=false, updatable=false)
    @ManyToOne
    private Client client;

    @Column(name = "date")
    private Date datePackClient;

}
