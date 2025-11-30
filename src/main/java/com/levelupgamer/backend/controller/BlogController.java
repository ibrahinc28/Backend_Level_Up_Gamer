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
    public List<Blog> getAllBlog(@RequestParam(required = false) String type,
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
        public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
            return blogService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

        }

        @PostMapping
        public ResponseEntity<Blog> crearBlog(@RequestBody Blog blog) {
            Blog nuevoBlog = blogService.guardarBlog(blog);
            return new ResponseEntity<>(nuevoBlog,HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Blog> actualizarBlog(@PathVariable Long id, @RequestBody Blog blog) {
            Blog actualizado = blogService.actualizarBlog(id, blog);
            if(actualizado != null){
                return ResponseEntity.ok(actualizado);
            }
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarBlog(@PathVariable Long id){
            blogService.eliminarBlog(id);
            return ResponseEntity.noContent().build();
        }
        
    }

