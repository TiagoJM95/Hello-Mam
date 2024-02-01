package com.mindera.repositories;

import com.mindera.entities.Videogame;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

public interface VideogameExtensionRepository extends PanacheMongoRepository<Videogame> {

}
