package com.phimmoi.techwizapi.repository;

import com.phimmoi.techwizapi.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    boolean existsByName(String name);
}
