package com.mindera.converter;

import com.mindera.dto.MovieDto;
import com.mindera.entity.Movie;
import com.mindera.entity.builders.MovieBuilder;

import java.util.List;

public class MovieConverter {

    public static MovieDto fromMovieToMovieDto(Movie movie){
        return new MovieDto(
                movie.getTitle(),
                movie.getDirector(),
                movie.getGenre(),
                movie.getRating()
                );


    }

    public static List<MovieDto> ListMovieToListMovieDto(List<Movie> movies){
        return movies.stream().map(MovieConverter::fromMovieToMovieDto).toList();
    }
    public static Movie fromMovieDtoToMovie(MovieDto movie){
        MovieBuilder builder = new MovieBuilder();
        builder.setTitle(movie.getTitle());
        builder.setDirector(movie.getDirector());
        builder.setRating(movie.getRating());
        builder.setGenre(movie.getGenre());

        return builder.getResult();

    }
}
