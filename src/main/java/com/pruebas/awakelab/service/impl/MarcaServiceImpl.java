package com.pruebas.awakelab.service.impl;

import com.pruebas.awakelab.model.Marca;
import com.pruebas.awakelab.repo.IMarcaRepo;
import com.pruebas.awakelab.service.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements IMarcaService {

    @Autowired
    private IMarcaRepo repo;

    @Override
    public Marca register(Marca marca) {
        return repo.save(marca);
    }

    @Override
    public Marca modify(Marca marca) {
        return repo.save(marca);
    }

    @Override
    public boolean delete(Integer id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public Marca findById(Integer id) {
        Optional<Marca> op = repo.findById(id);
        return op.orElseGet(Marca::new);
    }

    @Override
    public List<Marca> findAll() {
        return repo.findAll();
    }
}
