package com.levelupgamer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.levelupgamer.backend.model.Carrito;
import java.util.List;
import java.time.LocalDateTime;


public interface CarritoRepository extends JpaRepository<Carrito, Long>{

    List<Carrito> findByFechaCompraBetween(LocalDateTime starDateTime, LocalDateTime endDateTime);

    @Query("SELECT c FROM Carrito c WHERE c.totalPagar > :minTotal")
    List<Carrito> findByTotalGreaterThan(@Param("minTotal") Double minTotal);
}
