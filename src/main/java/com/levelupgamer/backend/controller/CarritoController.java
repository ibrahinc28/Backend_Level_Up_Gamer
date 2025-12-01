package com.levelupgamer.backend.controller;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Carrito> crearCarrito(@RequestBody Carrito carrito) {
        Carrito nuevaCarrito = carritoService.guardarCarrito(carrito);
        return new ResponseEntity<>(nuevaCarrito, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Carrito> obtenerTodas() {
        return carritoService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> obtenerCarritoPorId(@PathVariable Long id) {
        Optional<Carrito> carrito = carritoService.obtenerPorId(id);

        if (carrito.isPresent()) {
            return ResponseEntity.ok(carrito.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable Long id, @RequestBody Carrito carritoDetalles) {
        Carrito actualizado = carritoService.actualizaCarrito(id, carritoDetalles);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id){
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }
}