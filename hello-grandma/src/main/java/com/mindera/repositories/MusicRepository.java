package com.mindera.repositories;

import com.mindera.entities.Music;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

public interface MusicRepository extends PanacheMongoRepository<Music> {
}