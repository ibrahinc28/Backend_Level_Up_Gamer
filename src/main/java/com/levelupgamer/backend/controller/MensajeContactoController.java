package com.levelupgamer.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelupgamer.backend.service.MensajeContactoService;

@RestController
@RequestMapping("/api/contactos")
public class MensajeContactoController {
    
    @Autowired
    private MensajeContactoService mensajeContactoService;
}
