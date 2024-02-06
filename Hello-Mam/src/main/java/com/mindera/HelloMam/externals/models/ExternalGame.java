package com.mindera.HelloMam.externals.models;

import lombok.*;
import java.io.Serializable;
import java.util.List;

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
