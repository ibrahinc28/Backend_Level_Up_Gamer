package com.levelupgamer.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelupgamer.backend.model.Orden;
import com.levelupgamer.backend.repository.OrdenRepository;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public Orden guardarOrden(Orden orden){
        orden.setFechaCompra(LocalDateTime.now());
        return ordenRepository.save(orden);
    }

    public List<Orden> obtenerTodas(){
        return ordenRepository.findAll();
    }

}