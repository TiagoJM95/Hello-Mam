package com.mindera.HelloMam.externals.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalGame implements Serializable {

    private Long igdbId;

    private String name;

    private String releaseDate;

    private Double rating;

    private List<String> genreIds;

    boolean fromIGDB;
}