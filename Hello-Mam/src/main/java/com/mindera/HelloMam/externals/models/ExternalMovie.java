package com.mindera.HelloMam.externals.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ExternalMovie implements Serializable {
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
    @JsonProperty("genres")
    private List<String> genres;
}