package com.mindera.ze;

import com.mindera.entities.Videogame;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

public interface IGDBRepository extends PanacheMongoRepository<Videogame> {

}
