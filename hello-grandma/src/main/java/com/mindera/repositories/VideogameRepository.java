package com.mindera.repositories;

import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VideogameRepository implements PanacheMongoRepository<Videogame> {

    public Videogame findByIgdbId(int igdbId){
        return find("igdbId", igdbId).firstResult();
    }

    public Videogame findByTitle(String title){
        return find("title", title).firstResult();
    }

}