package com.levelupgamer.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelupgamer.backend.model.Carrito;
import com.levelupgamer.backend.repository.CarritoRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito guardarOrden(Carrito carrito){
        carrito.setFechaCompra(LocalDateTime.now());
        return carritoRepository.save(carrito);
    }

    public List<Carrito> obtenerTodas(){
        return carritoRepository.findAll();
    }

    public Carrito actualizaOrden(Long id, Carrito detallesOrden){
        return carritoRepository.findById(id).map(ordenExistente -> {
            ordenExistente.setSubtotal(detallesOrden.getSubtotal());
            ordenExistente.setItemsJson(detallesOrden.getItemsJson());
            ordenExistente.setCostoEnvio(detallesOrden.getCostoEnvio());
            ordenExistente.setTotalPagar(detallesOrden.getTotalPagar());
            ordenExistente.setFechaCompra(detallesOrden.getFechaCompra());
            return carritoRepository.save(ordenExistente);
        }).orElse(null);
    }

    public void eliminarOrden(Long id){
        carritoRepository.deleteById(id);
    }
}