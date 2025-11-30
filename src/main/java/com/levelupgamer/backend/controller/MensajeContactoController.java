package com.levelupgamer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.levelupgamer.backend.model.MensajeContacto;
import com.levelupgamer.backend.service.MensajeContactoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/contactos")
public class MensajeContactoController {
    
    @Autowired
    private MensajeContactoService mensajeContactoService;

    @PostMapping
    public ResponseEntity<MensajeContacto> crearMensaje(@RequestBody MensajeContacto mensaje){
        MensajeContacto nuevoMensaje = mensajeContactoService.guardarMensaje(mensaje);
        return new ResponseEntity<>(nuevoMensaje, HttpStatus.CREATED);
    }

    @GetMapping("/nuevos")
    public List<MensajeContacto> getNewMessages() {
        return mensajeContactoService.obtenerMensajesNuevos();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<MensajeContacto> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        MensajeContacto actualizado = mensajeContactoService.actualizarEstado(id, estado);
        if(actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }   
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long id){
        mensajeContactoService.eliminarMensaje(id);
        return ResponseEntity.noContent().build();
    }
    
}
