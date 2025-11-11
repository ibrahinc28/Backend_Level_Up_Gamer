package com.levelupgamer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelupgamer.backend.model.Blog;
import com.levelupgamer.backend.service.BlogService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> getAllArticulos(@RequestParam(required = false) String type,
                                          @RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()){
            return blogService.buscarPorTitulo(keyword);
        }

        if(type != null && !type.isEmpty()) {
            return blogService.obtenerPorTipo(type);
        }

        return blogService.obtenerTodos();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Blog> getArticuloById(@PathVariable Long id) {
            return blogService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

        }

        @PostMapping
        public ResponseEntity<Blog> crearArticulo(@RequestBody Blog blog) {
            Blog nuevoArticulo = blogService.guardarArticulo(blog);
            return new ResponseEntity<>(nuevoArticulo,HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Blog> actualizarArticulo(@PathVariable Long id, @RequestBody Blog blog) {
            Blog actualizado = blogService.actualizarArticulo(id, blog);
            if(actualizado != null){
                return ResponseEntity.ok(actualizado);
            }
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarArticulo(@PathVariable Long id){
            blogService.eliminarArticulo(id);
            return ResponseEntity.noContent().build();
        }
        
    }

