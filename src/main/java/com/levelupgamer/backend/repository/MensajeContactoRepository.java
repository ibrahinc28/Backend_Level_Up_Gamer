package com.levelupgamer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.levelupgamer.backend.model.MensajeContacto;

public interface MensajeContactoRepository extends JpaRepository <MensajeContacto, Long>{

    List<MensajeContacto> findByEmail(String email);

    List<MensajeContacto> findByStatusOrderByTimeStampDesc(String status);

    List<MensajeContacto> findBySubjectContainingOrderByTimeStampDesc(String keyword);

    @Query("SELECT COUNT(m) FROM MensajeContacto m WHERE m.status = 'Nuevo'")
    Long countNewMessages();
}
