package com.levelupgamer.backend.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.levelupgamer.backend.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>{

    List<Blog> findByTypeOrderByDateDesc(String type);

    List<Blog> findByTitleContainingOrderByDateDesc(String keyword);

    List<Blog> findByDateBetweenOrderByDateDesc(LocalDate starDate, LocalDate endDate);
}
