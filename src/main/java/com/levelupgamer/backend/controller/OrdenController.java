package com.levelupgamer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelupgamer.backend.model.Orden;
import com.levelupgamer.backend.service.OrdenService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



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

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizarOrden(@PathVariable Long id, @RequestBody Orden orden) {
        Orden actualizada = ordenService.actualizaOrden(id, orden);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id){
        ordenService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
}
