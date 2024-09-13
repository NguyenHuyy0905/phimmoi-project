package com.phimmoi.techwizapi.repository;

import com.phimmoi.techwizapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
