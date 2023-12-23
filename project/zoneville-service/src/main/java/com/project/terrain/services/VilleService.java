package com.project.terrain.services;


import com.project.terrain.IDao.IDao;
import com.project.terrain.entities.Ville;
import com.project.terrain.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService implements IDao<Ville> {

    @Autowired
    private VilleRepository repisitory;

    @Override
    public Ville create(Ville o) {
        return repisitory.save(o);
    }

    @Override
    public Ville update(Ville o) {
        return repisitory.save(o);
    }

    @Override
    public Boolean delete(Ville o) {
        try {
            repisitory.delete(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Ville findById(int id) {
        return repisitory.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Ville> findAll() {
        return repisitory.findAll();
    }
}
