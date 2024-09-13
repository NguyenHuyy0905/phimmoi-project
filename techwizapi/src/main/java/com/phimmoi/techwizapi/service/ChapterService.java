package com.phimmoi.techwizapi.service;

import com.phimmoi.techwizapi.dto.request.ChapterCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface ChapterService {
    Long create(ChapterCreateRequest chapterRequest);
    Long delete(Long id);
}
