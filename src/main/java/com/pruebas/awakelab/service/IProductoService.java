package com.pruebas.awakelab.service;

import com.pruebas.awakelab.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService extends ICRUD<Producto>{

    Page<Producto> listarPageable(Pageable pageable);
    List<Producto> findByPattern(String pattern);
    List<Producto> findByMarca(String marca);
}
