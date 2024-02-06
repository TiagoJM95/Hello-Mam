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

@MongoEntity(collection="videogames", database = "helloGrandma")
public class Videogame extends PanacheMongoEntity {
    @JsonProperty("id")
    private int igdbId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("first_release_date")
    private String releaseDate;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("genres")
    private List<Integer> genreIds;

    @JsonProperty("similar_games")
    private List<String> similarGames;

    boolean fromIGDB;
}