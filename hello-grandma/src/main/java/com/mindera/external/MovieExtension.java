package com.mindera.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter

public class MovieExtension {
    @JsonProperty("results")
    private List<MovieResponse> results;

    @JsonIgnoreProperties(ignoreUnknown=true)
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MovieResponse {
        @JsonProperty("id")
        private Long tmdbId;
        @JsonProperty("title")
        private String title;
        @JsonProperty("release_date")
        private String releaseDate;
        @JsonProperty("vote_average")
        private Double voteAverage;
        @JsonProperty("genre_ids")
        private List<Integer> genreIds;
    }
}