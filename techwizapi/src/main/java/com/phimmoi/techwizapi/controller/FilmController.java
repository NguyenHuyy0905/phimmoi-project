package com.phimmoi.techwizapi.controller;

import com.phimmoi.techwizapi.dto.ApiResponse;
import com.phimmoi.techwizapi.dto.request.FilmCreateRequest;
import com.phimmoi.techwizapi.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/film")
@RequiredArgsConstructor
@Validated
public class FilmController {
    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<ApiResponse.Response> createFilm(@RequestBody @Valid FilmCreateRequest filmCreateRequest) {
        return ApiResponse.created(filmService.createFilm(filmCreateRequest));
    }
}
