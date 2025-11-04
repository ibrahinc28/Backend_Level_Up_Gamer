package com.levelupgamer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levelupgamer.backend.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    // List<Producto> findByCategoria(String categoria);
}
