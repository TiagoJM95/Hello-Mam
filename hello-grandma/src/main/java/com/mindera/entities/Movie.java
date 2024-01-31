package com.mindera.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@MongoEntity(collection="movies")
public class Movie extends PanacheMongoEntity {
    @JsonProperty("id")
    private Long tmdbId;
    private String title;
    private String release_date;
    private Double vote_average;
    private String overview;
    private String status;
    private String tagline;
    private Integer runtime;
    private String original_language;
    private String revenue;
    private String budget;
    private String popularity;
    private String vote_count;
    private List<Object> genres;
}