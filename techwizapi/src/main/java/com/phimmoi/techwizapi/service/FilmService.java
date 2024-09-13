package com.phimmoi.techwizapi.service;

import com.phimmoi.techwizapi.dto.request.FilmCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface FilmService {
    Long createFilm(FilmCreateRequest film);
}
