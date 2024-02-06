package com.mindera.repositories;

import com.mindera.entities.Videogame;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VideogameRepository implements PanacheMongoRepository<Videogame> {

    public Optional<Videogame> findByIgdbId(int igdbId) {
        return find("igdbId", igdbId).firstResultOptional();
    }

    public List<Videogame> findByTitle(String title){
        return find("title", title).list();
    }

}