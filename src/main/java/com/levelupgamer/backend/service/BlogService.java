package com.levelupgamer.backend.service;

import java.util.List;
import java.util.Optional;
import com.levelupgamer.backend.repository.BlogRepository;
import org.springframework.stereotype.Service;

import com.levelupgamer.backend.model.Blog;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> obtenerTodos(){
        return blogRepository.findAll();
    }

    public Optional<Blog> obtenerPorId(Long id) {
        return blogRepository.findById(id);
    }

    public List<Blog> obtenerPorTipo(String type){
        return blogRepository.findByTypeOrderByDateDesc(type);
    }

    public List<Blog> buscarPorTitulo(String keyword) {
        return blogRepository.findByTitleContainingOrderByDateDesc(keyword);
    }

    public Blog guardarArticulo(Blog blog){
        return blogRepository.save(blog);

    }

    public Blog actualizarArticulo(Long id, Blog detallesArticulo){
        return blogRepository.findById(id).map(articuloExistente ->{
            articuloExistente.setTitle(detallesArticulo.getTitle());
            articuloExistente.setContent(detallesArticulo.getContent());
            articuloExistente.setType(detallesArticulo.getType());
            articuloExistente.setDate(detallesArticulo.getDate());
            articuloExistente.setImageSrc(detallesArticulo.getImageSrc());
            articuloExistente.setSummary(detallesArticulo.getSummary());

            return blogRepository.save(articuloExistente);
        }).orElse(null);
    }

    public void eliminarArticulo(Long id){
        blogRepository.deleteById(id);
    }
}
