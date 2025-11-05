package com.levelupgamer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelupgamer.backend.model.Orden;
import com.levelupgamer.backend.service.OrdenService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping()
    public ResponseEntity<Orden> crearObtener(@RequestBody Orden orden) {
        Orden nuevaOrden = ordenService.guardarOrden(orden);
        return new ResponseEntity<>(nuevaOrden, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Orden> obtenerTodas() {
        return ordenService.obtenerTodas();
    }

    
}
