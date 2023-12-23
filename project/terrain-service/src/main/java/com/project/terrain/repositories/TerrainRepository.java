package com.project.terrain.repositories;

import com.project.terrain.entities.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerrainRepository  extends JpaRepository<Terrain,Long> {
}
