package com.pruebas.awakelab.repo;

import com.pruebas.awakelab.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepo extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombreStartingWith(String pattern);

    @Query("from Producto p where p.marca.nombre like CONCAT('%',:nombreMarca,'%')")
    List<Producto> findbyMarca(@Param("nombreMarca") String nombreMarca);
}
