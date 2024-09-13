package com.phimmoi.techwizapi.service;

import org.springframework.stereotype.Service;

@Service
public interface FilmPersonService {
    Long create(Long filmId, Long personId);
    Long delete(Long id);
}
