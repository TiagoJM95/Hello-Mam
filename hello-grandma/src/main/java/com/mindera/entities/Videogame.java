package com.mindera.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@MongoEntity(collection="videogames")
public class Videogame {
}