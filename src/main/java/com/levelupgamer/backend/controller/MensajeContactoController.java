package com.levelupgamer.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelupgamer.backend.model.MensajeContacto;
import com.levelupgamer.backend.service.MensajeContactoService;

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
}
