package com.mindera.converters;

import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.enums.MovieGenres;

import java.util.ArrayList;
import java.util.List;

public class MovieConverter {

    public static MovieGenres getGenreById(Integer id){
        for(MovieGenres movieGenres : MovieGenres.values()){
            if(movieGenres.getId() == id){
                return movieGenres;
            }
        }
        return null;
    }

    /*public static List<MovieGenres> fromIntegerListToGenreList(List<Integer> genreIds) {
        List<MovieGenres> genres = new ArrayList<>();
        for (Integer genreId : genreIds) {
            genres.add(getGenreById(genreId));
        }
        return genres;
    }

    public static List<String> fromGenreListToStringList(List<Integer> genreIds) {
        List<String> genres = new ArrayList<>();
        for (Integer genreId : genreIds) {
            genres.add(MovieGenres.getMovieGenreById(genreId).getName());
        }
        return genres;
    }*/

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