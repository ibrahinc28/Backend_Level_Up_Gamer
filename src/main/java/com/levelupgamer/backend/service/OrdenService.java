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

    public Orden actualizaOrden(Long id, Orden detallesOrden){
        return ordenRepository.findById(id).map(ordenExistente -> {
            ordenExistente.setSubtotal(detallesOrden.getSubtotal());
            ordenExistente.setItemsJson(detallesOrden.getItemsJson());
            ordenExistente.setCostoEnvio(detallesOrden.getCostoEnvio());
            ordenExistente.setTotalPagar(detallesOrden.getTotalPagar());
            ordenExistente.setFechaCompra(detallesOrden.getFechaCompra());
            return ordenRepository.save(ordenExistente);
        }).orElse(null);
    }

    public void eliminarOrden(Long id){
        ordenRepository.deleteById(id);
    }
}