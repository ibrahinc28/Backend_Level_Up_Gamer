package com.levelupgamer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levelupgamer.backend.model.DatosUsuario;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de la entidad
 * {@link DatosUsuario}.
 * Proporciona métodos para realizar consultas personalizadas además de las
 * operaciones CRUD básicas.
 */
@Repository
public interface UsuariosRepository extends JpaRepository<DatosUsuario, Long>{

}