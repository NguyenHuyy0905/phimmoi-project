package com.phimmoi.techwizapi.service.impl;

import com.phimmoi.techwizapi.dto.request.ChapterCreateRequest;
import com.phimmoi.techwizapi.dto.request.FilmCreateRequest;
import com.phimmoi.techwizapi.entity.Film;
import com.phimmoi.techwizapi.exception.BusinessException;
import com.phimmoi.techwizapi.repository.CountryRepository;
import com.phimmoi.techwizapi.repository.FilmRepository;
import com.phimmoi.techwizapi.repository.SubscriptionRepository;
import com.phimmoi.techwizapi.service.ChapterService;
import com.phimmoi.techwizapi.service.FilmPersonService;
import com.phimmoi.techwizapi.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final SubscriptionRepository subscriptionRepository;
    private final CountryRepository countryRepository;
    private final FilmRepository filmRepository;
    private final FilmPersonService filmPersonService;
    private final ChapterService chapterService;

    @Override
    public Long createFilm(FilmCreateRequest filmRequest) {

        if (!countryRepository.existsById(filmRequest.getCountryId())) {
            throw new BusinessException("Country not found");
        }

        if (!subscriptionRepository.existsById(filmRequest.getSubId())) {
            throw new BusinessException("Subscription not found");
        }

        Film film = Film.builder()
                .name(filmRequest.getName())
                .originName(filmRequest.getOriginName())
                .countryId(filmRequest.getCountryId())
                .releaseYear(filmRequest.getReleaseYear())
                .view(0L)
                .language(filmRequest.getLanguage())
                .description(filmRequest.getDescription())
                .quality(filmRequest.getQuality())
                .urlImage(filmRequest.getUrlImage())
                .urlTrailer(filmRequest.getUrlTrailer())
                .isComplete(filmRequest.getIsComplete())
                .subId(filmRequest.getSubId())
                .status(filmRequest.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy("Admin")
                .updatedBy("Admin")
                .build();

        Long filmId = filmRepository.save(film).getId();

        for (Long id : filmRequest.getPersonId()) {
            filmPersonService.create(filmId, id);
        }

        for (ChapterCreateRequest chapterCreateRequest : filmRequest.getChapterCreateRequests()) {
            chapterService.create(chapterCreateRequest);
        }

        return filmId;
    }
}
