package com.levelupgamer.backend.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.levelupgamer.backend.model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long>{

    List<Articulo> findByTypeOrderByDateDesc(String type);

    List<Articulo> findByTitleContainingOrderByDateDesc(String keyword);

    List<Articulo> findByDateBetweenOrderByDateDesc(LocalDate starDate, LocalDate endDate);
}
