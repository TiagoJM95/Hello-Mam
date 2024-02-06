package com.mindera.HelloMam.externals.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ExternalMovie {

    private Long tmdbId;


    private String title;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private Double voteAverage;


    private String overview;


    private String status;


    private String tagline;


    private Integer runtime;

    @JsonProperty("original_language")
    private String originalLanguage;


    private String revenue;

    /*@JsonProperty("budget")
    private String budget;

    @JsonProperty("popularity")
    private String popularity;

    @JsonProperty("vote_count")
    private String voteCount;*/

    @JsonProperty("genres")
    private List<String> genres;
}