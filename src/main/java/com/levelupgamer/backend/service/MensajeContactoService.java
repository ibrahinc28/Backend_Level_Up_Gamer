package com.levelupgamer.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelupgamer.backend.model.MensajeContacto;
import com.levelupgamer.backend.repository.MensajeContactoRepository;

@Service
public class MensajeContactoService {

    @Autowired
    private MensajeContactoRepository mensajeContactoRepository;

    public MensajeContacto guardarMensaje(MensajeContacto mensaje) {
        mensaje.setTimeStamp(LocalDateTime.now());
        mensaje.setStatus("Nuevo");
        return mensajeContactoRepository.save(mensaje);

    }

    public List<MensajeContacto> obtenerMensajesNuevos(){
        final String STATUS_NUEVO = "Nuevo";
        return mensajeContactoRepository.findByStatusOrderByTimestampDesc(STATUS_NUEVO);

    }

    public Optional<MensajeContacto> obtenerPorId(Long id){
        return mensajeContactoRepository.findById(id);
    }

    public MensajeContacto actualizarEstado(Long id, String nuevoEstado){
        return mensajeContactoRepository.findById(id).map(mensajeExitente ->{
            mensajeExitente.setStatus(nuevoEstado);
            return mensajeContactoRepository.save(mensajeExitente);
        }).orElse(null);
    }

    public void eliminarMensaje(Long id){
        mensajeContactoRepository.deleteById(id);
    }
    
}
