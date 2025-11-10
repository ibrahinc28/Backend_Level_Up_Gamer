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
}
