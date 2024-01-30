/*
package com.mindera.external;

import java.util.ArrayList;
import java.util.List;

public class MovieExtensionConverter {

    public static String getGenreById(Integer id){
        return switch (id) {
            case 28 -> "Action";
            case 12 -> "Adventure";
            case 16 -> "Animation";
            case 35 -> "Comedy";
            case 80 -> "Crime";
            case 99 -> "Documentary";
            case 18 -> "Drama";
            case 10751 -> "Family";
            case 14 -> "Fantasy";
            case 36 -> "History";
            case 27 -> "Horror";
            case 10402 -> "Music";
            case 9648 -> "Mystery";
            case 10749 -> "Romance";
            case 878 -> "Science Fiction";
            case 10770 -> "TV Movie";
            case 53 -> "Thriller";
            case 10752 -> "War";
            case 37 -> "Western";
            default -> "Unknown";
        };
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
*/
