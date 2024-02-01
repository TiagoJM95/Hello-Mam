package com.mindera.repositories;

import com.mindera.entities.Movie;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieRepository implements PanacheMongoRepository<Movie> {

    public Optional<Movie> findByTmdbId(Long id) {
        return find("tmdbId", id).firstResultOptional();
    }
    public List<Movie> findByTitle(String title) {
        return find("title", title).list();
    }
}