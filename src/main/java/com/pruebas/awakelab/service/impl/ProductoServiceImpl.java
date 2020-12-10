package com.pruebas.awakelab.service.impl;

import com.pruebas.awakelab.model.Producto;
import com.pruebas.awakelab.repo.IProductoRepo;
import com.pruebas.awakelab.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepo repo;

    @Override
    public Producto register(Producto producto) {
        producto.setNombre(producto.getNombre().toLowerCase());
        return repo.save(producto);
    }

    @Override
    public Producto modify(Producto producto) {
        producto.setNombre(producto.getNombre().toLowerCase());
        return repo.save(producto);
    }

    @Override
    public boolean delete(Integer id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public Producto findById(Integer id) {
         Optional<Producto> op = repo.findById(id);
        return op.orElseGet(Producto::new);
    }

    @Override
    public List<Producto> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<Producto> listarPageable(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Producto> findByPattern(String pattern) {
        return repo.findByNombreStartingWith(pattern);
    }
}
