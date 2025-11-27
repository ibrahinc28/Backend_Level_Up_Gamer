package com.levelupgamer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.levelupgamer.backend.repository.UsuariosRepository;

import jakarta.transaction.Transactional;

import com.levelupgamer.backend.model.DatosUsuario;

@Service
@Transactional
public class UsuariosService {

    @Autowired
    private UsuariosRepository repositorio;

    /**
     * Obtiene una lista de todos los usuarios en la base de datos.
     * 
     * @return Lista de objetos {@link Curso}.
     */
    public List<DatosUsuario> verUsuarios() {
        return repositorio.findAll();
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id ID del usuario a buscar, tipo Long.
     * @return Objeto {@link DatosUsuario} correspondiente al ID proporcionado o
     *         {@code null} si no se encuentra.
     */
    public DatosUsuario buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    /**
     * Guarda un nuevo usuario en el sistema.
     * 
     * @param modelo Objeto {@link DatosUsuario} con la información del usuario a
     *                 guardar.
     * @return Objeto {@link DatosUsuario} guardado o existente.
     */
    public DatosUsuario guardarUsuario(DatosUsuario modelo) {
        DatosUsuario datosguardados = new DatosUsuario();
        datosguardados.setNombre(modelo.getNombre());
        datosguardados.setSnombre(modelo.getSnombre());
        datosguardados.setApellidopat(modelo.getApellidopat());
        datosguardados.setApellidomat(modelo.getApellidomat());
        datosguardados.setContrasena(modelo.getContrasena());
        datosguardados.setCorreo(modelo.getCorreo());

        DatosUsuario test = repositorio.save(datosguardados);

        return test;
    }

     /**
     * Elimina un usuario del sistema por su ID.
     * 
     * @param id ID del usuario a eliminar.
     */
    public void borrarUsuario(Long id) {
        repositorio.deleteById(id);
    }

}
