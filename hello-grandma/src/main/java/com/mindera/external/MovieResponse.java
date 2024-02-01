package com.mindera.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter

public class MovieResponse {
    @JsonProperty("results")
    private List<MovieExtension> results;
}