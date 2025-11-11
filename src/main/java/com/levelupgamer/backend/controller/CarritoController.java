package com.levelupgamer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelupgamer.backend.model.Carrito;
import com.levelupgamer.backend.service.CarritoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping()
    public ResponseEntity<Carrito> crearObtener(@RequestBody Carrito carrito) {
        Carrito nuevaOrden = carritoService.guardarOrden(carrito);
        return new ResponseEntity<>(nuevaOrden, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Carrito> obtenerTodas() {
        return carritoService.obtenerTodas();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizarOrden(@PathVariable Long id, @RequestBody Carrito ordenDetalles) {
        Carrito actualizado = carritoService.actualizaOrden(id, ordenDetalles);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id){
        carritoService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
}
