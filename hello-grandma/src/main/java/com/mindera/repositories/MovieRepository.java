package com.mindera.repositories;

import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import java.util.List;

public interface MovieRepository extends PanacheMongoRepository<Movie> {
    List<MovieGetDto> findByDirector(String director);
}