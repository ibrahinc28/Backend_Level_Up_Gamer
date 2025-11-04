package com.levelupgamer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.levelupgamer.backend.model.Orden;
import java.util.List;
import java.time.LocalDateTime;


public interface OrdenRepository extends JpaRepository<Orden, Long>{

    List<Orden> findByFechaCompraBetween(LocalDateTime starDateTime, LocalDateTime endDateTime);

    @Query("SELECT o FROM Orden o WHERE Oo.totalPagar > :minTotal")
    List<Orden> findByTotalGreaterThan(@Param("minTotal") Double minTotal);
}
