package com.levelupgamer.backend.service;

import java.time.LocalDateTime;
import java.util.List;

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
    
}
