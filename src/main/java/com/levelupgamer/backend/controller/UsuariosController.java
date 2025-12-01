package com.levelupgamer.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.levelupgamer.backend.service.UsuariosService;
import com.levelupgamer.backend.model.DatosUsuario;
import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los
 * usuarios.
 * Proporciona endpoints para listar, obtener, crear y eliminar
 * usuarios.
 * 
 * <p>Este controlador utiliza el servicio {@link UsuariosService} para realizar
 * las operaciones CRUD sobre los usuarios.</p>
 */
@RestController
@RequestMapping("api/usuarios")
@CrossOrigin(origins = "http://frontend-levelupgamer.s3-website-us-east-1.amazonaws.com") // para permitir el front de Vite
public class UsuariosController {
    @Autowired
    private UsuariosService servicio;

    /**
     * Obtiene una lista de todos los usuarios.
     * @return Lista de objetos {@link DatosUsuario}.
     */
    @GetMapping
    public ResponseEntity<List<DatosUsuario>> listar() { // Método para listar todos los usuario
        List<DatosUsuario> usuarios = servicio.verUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Guarda un nuevo usuario en el sistema.
     * 
     * @param usuario Objeto {@link DatosUsuario} con la información del usuario a
     *                 guardar.
     * @return Objeto {@link DatosUsuario} guardado.
     */
    @PostMapping
    public ResponseEntity<DatosUsuario> guardar(@RequestBody DatosUsuario usuario) { // Método para guardar un usuario
        DatosUsuario usuarionuevo = servicio.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarionuevo);
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id ID del usuario a buscar.
     * @return Objeto {@link DatosUsuario} correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DatosUsuario> buscar(@PathVariable Long id) { // Método para buscar un usuario por su ID
        try {
            DatosUsuario usuario = servicio.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un usuario del sistema por su ID.
     * 
     * @param id ID del usuario a eliminar.
     * @return Respuesta con código de estado HTTP correspondiente (204 (sin contenido) en caso de ser exitoso, 404 (no encontrado) si no).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) { // Método para borrar un usuario por su ID
        try {
            servicio.borrarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Modifica los datos de un usuario en el sistema por su ID.
     * 
     * @param id ID del usuario a modificar.
     * @return Respuesta con código de estado HTTP correspondiente (200 (éxito) en caso de ser exitoso, 404 (no encontrado) si no).
     */
    @PutMapping("/{id}")
    public ResponseEntity<DatosUsuario> actualizar(@PathVariable Long id, @RequestBody DatosUsuario usuario) {
        try {
            DatosUsuario user = servicio.buscarPorId(id);
            user.setId(user.getId());
            user.setNombre(user.getNombre());
            user.setSnombre(user.getSnombre());
            user.setApellidopat(user.getApellidopat());
            user.setApellidomat(user.getApellidomat());
            user.setContrasena(user.getContrasena());
            user.setCorreo(user.getCorreo());
            
            servicio.guardarUsuario(user);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}