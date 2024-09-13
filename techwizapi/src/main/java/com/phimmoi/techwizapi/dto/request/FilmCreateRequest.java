package com.phimmoi.techwizapi.dto.request;

import com.phimmoi.techwizapi.constant.enums.FilmStatus;
import com.phimmoi.techwizapi.constant.enums.Quality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmCreateRequest {
    @NotNull(message = "Movie name cannot be null")
    @NotBlank(message = "Movie name cannot be blank")
    private String name;
    private String originName;
    @NotNull(message = "Country name cannot be null")
    private Long countryId;
    @NotNull(message = "Release Year cannot be null")
    private Integer releaseYear;
    @NotNull(message = "Language cannot be null")
    private String language;
    private String description;
    @NotNull(message = "Quality cannot be null")
    private Quality quality;
    @NotNull(message = "Image cannot be null")
    private String urlImage;
    private String urlTrailer;
    private Boolean isComplete;
    @NotNull(message = "Subscription cannot be null")
    private Long subId;
    private FilmStatus status = FilmStatus.PENDING;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Set<Long> personId;
    private List<ChapterCreateRequest> chapterCreateRequests;
}
