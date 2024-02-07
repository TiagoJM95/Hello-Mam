package com.mindera.repositories;

import com.mindera.entities.Game;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GameRepository implements PanacheMongoRepository<Game> {

    public Optional<Game> findByIgdbId(int igdbId) {
        return find("igdbId", igdbId).firstResultOptional();
    }

    public List<Game> findByTitle(String title){
        return find("title", title).list();
    }

}