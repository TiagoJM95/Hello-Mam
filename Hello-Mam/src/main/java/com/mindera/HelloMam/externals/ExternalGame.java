package com.mindera.HelloMam.externals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalGame implements Serializable {

    private int igdbId;

    private String name;

    private String releaseDate;

    private Double rating;

    private List<Integer> genreIds;

    boolean fromIGDB;

    private List<String> similarGames;
}
