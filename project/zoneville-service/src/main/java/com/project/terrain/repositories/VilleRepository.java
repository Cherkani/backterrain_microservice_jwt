package com.project.terrain.repositories;

import com.project.terrain.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository  extends JpaRepository<Ville,Long> {
}
