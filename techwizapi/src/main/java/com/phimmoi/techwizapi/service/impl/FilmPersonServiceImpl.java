package com.phimmoi.techwizapi.service.impl;

import com.phimmoi.techwizapi.entity.FilmPerson;
import com.phimmoi.techwizapi.exception.BusinessException;
import com.phimmoi.techwizapi.repository.FilmPersonRepository;
import com.phimmoi.techwizapi.repository.FilmRepository;
import com.phimmoi.techwizapi.repository.PersonRepository;
import com.phimmoi.techwizapi.service.FilmPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmPersonServiceImpl implements FilmPersonService {
    private final FilmPersonRepository filmPersonRepository;
    private final PersonRepository personRepository;
    private final FilmRepository filmRepository;

    @Override
    public Long create(Long filmId, Long personId) {

        if (!personRepository.existsById(personId)) {
            throw new BusinessException("Person does not exist");
        }

        if (!filmRepository.existsById(filmId)) {
            throw new BusinessException("Film does not exist");
        }

        FilmPerson filmPerson = new FilmPerson();
        filmPerson.setFilmId(filmId);
        filmPerson.setPersonId(personId);
        return filmPersonRepository.save(filmPerson).getFilmId();
    }

    @Override
    public Long delete(Long id) {
        filmPersonRepository.deleteById(id);
        return id;
    }
}
