package com.levelupgamer.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelupgamer.backend.model.Carrito;
import com.levelupgamer.backend.repository.CarritoRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito guardarCarrito(Carrito carrito){
        carrito.setFechaCompra(LocalDateTime.now()); 
        return carritoRepository.save(carrito);
    }

    public List<Carrito> obtenerTodas(){
        return carritoRepository.findAll();
    }

    public Optional<Carrito> obtenerPorId(Long id) {
        return carritoRepository.findById(id);
    }

    public Carrito actualizaCarrito(Long id, Carrito detallesCarrito){
        return carritoRepository.findById(id).map(carritoExistente -> {
            carritoExistente.setSubtotal(detallesCarrito.getSubtotal());
            carritoExistente.setItemsJson(detallesCarrito.getItemsJson());
            carritoExistente.setCostoEnvio(detallesCarrito.getCostoEnvio());
            carritoExistente.setTotalPagar(detallesCarrito.getTotalPagar());
            carritoExistente.setFechaCompra(detallesCarrito.getFechaCompra());
            return carritoRepository.save(carritoExistente);
        }).orElse(null);
    }

    public void eliminarCarrito(Long id){
        carritoRepository.deleteById(id);
    }
}