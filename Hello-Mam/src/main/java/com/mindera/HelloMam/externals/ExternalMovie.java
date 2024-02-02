package com.mindera.HelloMam.externals;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ExternalMovie {
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
    private List<String> genres;
}