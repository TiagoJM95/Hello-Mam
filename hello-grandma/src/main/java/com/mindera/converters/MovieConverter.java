package com.mindera.converters;

import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;

public class MovieConverter {

    public static MovieGetDto fromEntityToGetDto(Movie movie){
        return new MovieGetDto(
                movie.id,
                movie.getTmdbId(),
                movie.getTitle(),
                movie.getReleaseDate(),
                movie.getVoteAverage(),
                movie.getOverview(),
                movie.getStatus(),
                movie.getTagline(),
                movie.getRuntime(),
                movie.getOriginalLanguage(),
                movie.getRevenue(),
                movie.getBudget(),
                movie.getPopularity(),
                movie.getVoteCount(),
                movie.getGenres()
        );
    }
}