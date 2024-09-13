package com.phimmoi.techwizapi.repository;

import com.phimmoi.techwizapi.entity.FilmPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmPersonRepository extends JpaRepository<FilmPerson, Long> {
}
