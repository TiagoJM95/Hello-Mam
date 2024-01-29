package com.mindera.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor

@MongoEntity(collection="musics")
public class Music extends PanacheMongoEntity {
}