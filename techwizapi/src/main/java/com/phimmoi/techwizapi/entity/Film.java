package com.phimmoi.techwizapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vi_title", nullable = false)
    private String viTitle;

    @Column(name = "en_title")
    private String enTitle;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column
    private Long view;

    @Column
    private String language;

    @Column
    private String description;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "url_trailer")
    private String urlTrailer;

    @Column
    private Float price;

    @Column(name = "requires_purchase", columnDefinition = "boolean default false")
    private Boolean requiresPurchase;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @ManyToMany
    @JoinTable(
            name = "film_tag",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "film_people",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "people_id")
    )
    private Set<Person> people = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "film")
    private Set<FilmUser> purchasedByUsers = new HashSet<>();
}
