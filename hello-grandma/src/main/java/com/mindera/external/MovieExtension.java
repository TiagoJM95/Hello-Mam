package com.mindera.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieExtension {
    @JsonProperty("id")
    private long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("originalLanguage")
    private String originalLanguage;
    @JsonProperty("originalTitle")
    private String originalTitle;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("releaseDate")
    private String releaseDate;
    @JsonProperty("voteAverage")
    private double voteAverage;
}
