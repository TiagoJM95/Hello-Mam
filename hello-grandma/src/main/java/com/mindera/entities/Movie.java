package com.mindera.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@MongoEntity(collection="movies")
public class Movie extends PanacheMongoEntity {
    private Long tmdbId;
    private String title;
    private LocalDate releaseDate;
    private int runtime;
    private List<String> genres;
    private String director;
}