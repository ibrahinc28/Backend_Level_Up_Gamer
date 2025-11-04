package com.levelupgamer.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

/**
 * Entidad que representa a un usuario en el sistema.
 * Contiene información como la ID única del usuario, su
 * nombre, segundo nombre (opcional), apellido paterno y
 * materno, su contraseña, y su correo.
 */
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor

public class DatosUsuario {

    /**
     * Identificador único del usuario.
     * Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Primer nombre del usuario.
     * Este campo es obligatorio.
     */
    @Column (name = "nombre", nullable = false)
    private String nombre;

    /**
     * Segundo nombre del usuario.
     * Este campo es opcional.
     */
    @Column (name = "snombre", nullable = true)
    private String snombre;

    /**
     * Apellido paterno del usuario.
     * Este campo es obligatorio.
     */
    @Column (name = "apellidopat", nullable = false)
    private String apellidopat;

    /**
     * Apellido materno del usuario.
     * Este campo es obligatorio.
     */
    @Column (name = "apellidomat", nullable = false)
    private String apellidomat;

    /**
     * Contraseña del usuario.
     * Este campo es obligatorio.
     */
    @Column (name = "contrasena", nullable = false)
    private String contrasena;

    /**
     * Correo electrónico del usuario.
     * Este campo es obligatorio.
     */
    @Column (name = "correo", nullable = false)
    private String correo;
}