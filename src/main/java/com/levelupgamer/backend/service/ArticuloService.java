package com.levelupgamer.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelupgamer.backend.model.Articulo;
import com.levelupgamer.backend.repository.ArticuloRepository;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    public List<Articulo> obtenerTodos(){
        return articuloRepository.findAll();
    }

    public Optional<Articulo> obtenerPorId(Long id) {
        return articuloRepository.findById(id);
    }

    public List<Articulo> obtenerPorTipo(String type){
        return articuloRepository.findByTypeOrderByDateDesc(type);
    }

    public List<Articulo> buscarPorTitulo(String keyword) {
        return articuloRepository.findByTitleContainingOrderByDateDesc(keyword);
    }
    public Articulo guardarArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    public Articulo actualizarArticulo(Long id, Articulo detallesArticulo){
        return articuloRepository.findById(id).map(articuloExistente ->{
            articuloExistente.setTitle(detallesArticulo.getTitle());
            articuloExistente.setContent(detallesArticulo.getContent());
            articuloExistente.setType(detallesArticulo.getType());
            articuloExistente.setDate(detallesArticulo.getDate());
            articuloExistente.setImageSrc(detallesArticulo.getImageSrc());
            articuloExistente.setSummary(detallesArticulo.getSummary());

            return articuloRepository.save(articuloExistente);
        }).orElse(null);
    }

    public void eliminarArticulo(Long id){
        articuloRepository.deleteById(id);
    }
}
