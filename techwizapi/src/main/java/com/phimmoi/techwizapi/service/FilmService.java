package com.phimmoi.techwizapi.service;

import com.phimmoi.techwizapi.entity.Film;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmService {
    public List<Film> getAllFilms();
}
