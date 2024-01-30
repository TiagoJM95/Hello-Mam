package com.mindera.external;

import java.util.ArrayList;
import java.util.List;

public class MovieExtensionConverter {

    public static String getGenreById(Integer id){
        switch (id){
            case 28:
                return "Action";
            case 12:
                return "Adventure";
            case 16:
                return "Animation";
            case 35:
                return "Comedy";
            case 80:
                return "Crime";
            case 99:
                return "Documentary";
            case 18:
                return "Drama";
            case 10751:
                return "Family";
            case 14:
                return "Fantasy";
            case 36:
                return "History";
            case 27:
                return "Horror";
            case 10402:
                return "Music";
            case 9648:
                return "Mystery";
            case 10749:
                return "Romance";
            case 878:
                return "Science Fiction";
            case 10770:
                return "TV Movie";
            case 53:
                return "Thriller";
            case 10752:
                return "War";
            case 37:
                return "Western";
            default:
                return "Unknown";
        }
    }

    public static List<String> convertFromIdListToStringList(List<Integer> genreIds) {
        List<String> genres = new ArrayList<>();
        for (Integer genreId : genreIds) {
            genres.add(getGenreById(genreId));
        }
        return genres;
    }

    public static MovieExtensionGetDto convert(MovieExtension movieExtension) {
        return new MovieExtensionGetDto(
                movieExtension.getId(),
                movieExtension.getTitle(),
                movieExtension.getReleaseDate(),
                movieExtension.getVoteAverage(),
                convertFromIdListToStringList(movieExtension.getGenreIds())
        );
    }

    public static List<MovieExtensionGetDto> convertList(List<MovieExtension> movieExtensions) {
        List<MovieExtensionGetDto> movieExtensionGetDtos = new ArrayList<>();
        for (MovieExtension movieExtension : movieExtensions) {
            movieExtensionGetDtos.add(convert(movieExtension));
        }
        return movieExtensionGetDtos;
    }
}
