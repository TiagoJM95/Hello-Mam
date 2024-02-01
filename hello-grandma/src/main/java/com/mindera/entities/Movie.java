package com.mindera.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonIgnore;

import java.util.ArrayList;
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

    @JsonProperty("title")
    private String title;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("genre_ids")
    private String overview;

    @JsonProperty("status")
    private String status;

    @JsonProperty("tagline")
    private String tagline;

    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("revenue")
    private String revenue;

    @JsonProperty("budget")
    private String budget;

    @JsonProperty("popularity")
    private String popularity;

    @JsonProperty("vote_count")
    private String voteCount;

    @JsonProperty("genres")
    @BsonIgnore
    private List<Object> excludedGenres;

    @JsonIgnore
    private List<String> genres = new ArrayList<>();
}