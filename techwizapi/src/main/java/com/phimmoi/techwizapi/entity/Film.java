package com.phimmoi.techwizapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "origin_name", length = 255)
    private String originName;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "view")
    private Long view;

    @Column(name = "language", length = 255)
    private String language;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "url_image", length = 255)
    private String urlImage;

    @Column(name = "url_trailer", length = 255)
    private String urlTrailer;

    @Column(name = "is_complete")
    private Boolean isComplete;

    @Column(name = "sub_id")
    private Long subId;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;
}
