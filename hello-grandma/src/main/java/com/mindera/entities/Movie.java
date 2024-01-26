package com.mindera.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@MongoEntity(collection="movies")
public class Movie extends PanacheMongoEntity {
    private String title;
    private int year;
    private int runtime;
    private List<String> genres;
    private String director;
}