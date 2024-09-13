package com.phimmoi.techwizapi.controller;

import com.phimmoi.techwizapi.dto.ApiResponse;
import com.phimmoi.techwizapi.dto.request.ChapterCreateRequest;
import com.phimmoi.techwizapi.service.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/chapter")
@RequiredArgsConstructor
@Validated
public class ChapterController {
    private final ChapterService chapterService;

    @PostMapping
    public ResponseEntity<ApiResponse.Response> createChapter(@RequestBody @Valid ChapterCreateRequest chapterCreateRequest) {
        return ApiResponse.created(chapterService.create(chapterCreateRequest));
    }

    @DeleteMapping("/{chapterId}")
    public ResponseEntity<ApiResponse.Response> deleteChapter(@PathVariable("chapterId") long chapterId) {
        return ApiResponse.ok(chapterService.delete(chapterId));
    }
}
