package com.project.terrain.services;


import com.project.terrain.IDao.IDao;
import com.project.terrain.entities.Zone;
import com.project.terrain.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ZoneService implements IDao<Zone> {

    @Autowired
    private ZoneRepository repisitory;

    @Override
    public Zone create(Zone o) {
        return repisitory.save(o);
    }

    @Override
    public Zone update(Zone o) {
        return repisitory.save(o);
    }

    @Override
    public Boolean delete(Zone o) {
        try {
            repisitory.delete(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Zone findById(int id) {
        return repisitory.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Zone> findAll() {
        return repisitory.findAll();
    }
}