package com.mindera.repositories;

import com.mindera.entities.Videogame;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

public interface VideogameRepository extends PanacheMongoRepository<Videogame> {
}