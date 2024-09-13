package com.phimmoi.techwizapi.controller;

import com.phimmoi.techwizapi.dto.ApiResponse;
import com.phimmoi.techwizapi.service.FilmPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/person")
@RequiredArgsConstructor
public class FilmPersonController {
    private final FilmPersonService filmPersonService;

    @PostMapping
    public ResponseEntity<ApiResponse.Response> addFilmPerson(@RequestParam("filmId") Long filmId, @RequestParam("personId") Long personId) {
        return ApiResponse.created(filmPersonService.create(filmId, personId));
    }

    @DeleteMapping("/{filmPersonId}")
    public ResponseEntity<ApiResponse.Response> deleteFilmPerson(@PathVariable Long filmPersonId) {
        return ApiResponse.ok(filmPersonService.delete(filmPersonId));
    }
}
