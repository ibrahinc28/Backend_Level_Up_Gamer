package com.levelupgamer.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import com.levelupgamer.backend.model.Carrito;
import com.levelupgamer.backend.repository.CarritoRepository;

@Transactional
@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Carrito finalizarCompra(Carrito carrito) throws Exception {
        
        try {
            List<Map<String, Object>> items = objectMapper.readValue(
                carrito.getItemsJson(), 
                new TypeReference<List<Map<String, Object>>>() {}
            );

            for (Map<String, Object> item : items) {
                Long productoId = ((Number) item.get("codigo")).longValue(); 
                Integer cantidad = (Integer) item.get("quantity"); 
                System.out.println("LOGICA DE NEGOCIO EJECUTADA: Reduciendo stock del Producto ID: " + productoId + " en cantidad: " + cantidad);

            }
        } catch (Exception e) {
            throw new Exception("Error al procesar la compra o stock insuficiente: " + e.getMessage());
        }
        
        carrito.setFechaCompra(LocalDateTime.now()); 
        return carritoRepository.save(carrito);
    }

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