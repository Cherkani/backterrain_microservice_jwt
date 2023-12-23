package com.project.terrain.repositories;

import com.project.terrain.entities.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackRepository extends JpaRepository<Pack,Long> {
}
