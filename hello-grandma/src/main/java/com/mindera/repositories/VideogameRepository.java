package com.mindera.repositories;

import com.mindera.entities.Videogame;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VideogameRepository implements PanacheMongoRepository<Videogame> {
}