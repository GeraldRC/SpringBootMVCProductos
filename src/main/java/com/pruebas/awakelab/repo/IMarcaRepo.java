package com.pruebas.awakelab.repo;

import com.pruebas.awakelab.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMarcaRepo extends JpaRepository<Marca, Integer> {
}
