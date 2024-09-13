package com.phimmoi.techwizapi.service.impl;

import com.phimmoi.techwizapi.dto.request.ChapterCreateRequest;
import com.phimmoi.techwizapi.entity.Chapter;
import com.phimmoi.techwizapi.exception.BusinessException;
import com.phimmoi.techwizapi.repository.ChapterRepository;
import com.phimmoi.techwizapi.repository.FilmRepository;
import com.phimmoi.techwizapi.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {
    private final FilmRepository filmRepository;
    private final ChapterRepository chapterRepository;

    @Override
    public Long create(ChapterCreateRequest chapterRequest) {

        if (chapterRepository.existsByName(chapterRequest.getName())) {
            throw new BusinessException("Name already exists");
        }

        if (!filmRepository.existsById(chapterRequest.getFilmId())) {
            throw new BusinessException("Film does not exist");
        }

        Chapter chapter = Chapter.builder()
                .name(chapterRequest.getName())
                .filmId(chapterRequest.getFilmId())
                .urlVideo(chapterRequest.getUrlVideo())
                .build();

        return chapterRepository.save(chapter).getId();
    }

    @Override
    public Long delete(Long id) {
        chapterRepository.deleteById(id);
        return id;
    }
}
