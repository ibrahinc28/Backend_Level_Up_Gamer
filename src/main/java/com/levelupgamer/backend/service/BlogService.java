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

    public Blog guardarBlog(Blog blog){
        return blogRepository.save(blog);

    }

    public Blog actualizarBlog(Long id, Blog detallesBlog){
        return blogRepository.findById(id).map(blogExistente ->{
            blogExistente.setTitle(detallesBlog.getTitle());
            blogExistente.setContent(detallesBlog.getContent());
            blogExistente.setType(detallesBlog.getType());
            blogExistente.setDate(detallesBlog.getDate());
            blogExistente.setImageSrc(detallesBlog.getImageSrc());
            blogExistente.setSummary(detallesBlog.getSummary());

            return blogRepository.save(blogExistente);
        }).orElse(null);
    }

    public void eliminarBlog(Long id){
        blogRepository.deleteById(id);
    }
}
