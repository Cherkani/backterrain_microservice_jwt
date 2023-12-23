package com.project.terrain.services;


import com.project.terrain.IDao.IDao;
import com.project.terrain.entities.Terrain;
import com.project.terrain.repositories.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerrainService implements IDao<Terrain> {

    @Autowired
    private TerrainRepository repisitory;

    @Override
    public Terrain create(Terrain o) {
        return repisitory.save(o);
    }

    @Override
    public Terrain update(Terrain o) {
        return repisitory.save(o);
    }

    @Override
    public Boolean delete(Terrain o) {
        try {
            repisitory.delete(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Terrain findById(Long id) {
        return repisitory.findById(id).orElse(null);
    }

    @Override
    public List<Terrain> findAll() {
        return repisitory.findAll();
    }
}
