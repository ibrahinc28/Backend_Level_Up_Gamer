package com.levelupgamer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelupgamer.backend.model.Articulo;
import com.levelupgamer.backend.service.ArticuloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public List<Articulo> getAllArticulos(@RequestParam(required = false) String type,
                                          @RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()){
            return articuloService.buscarPorTitulo(keyword);
        }

        if(type != null && !type.isEmpty()) {
            return articuloService.obtenerPorTipo(type);
        }

        return articuloService.obtenerTodos();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Articulo> getArticuloById(@PathVariable Long id) {
            return articuloService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

        }
    }

