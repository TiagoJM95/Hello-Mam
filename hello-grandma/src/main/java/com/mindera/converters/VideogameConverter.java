package com.mindera.converters;

import com.mindera.dtos.VideogameGetDto;
import com.mindera.entities.Videogame;
import com.mindera.enums.GameGenres;
import java.util.ArrayList;
import java.util.List;

public class VideogameConverter {

    public static List<String> convertFromListGenreIdsToListGenreString(List<Integer> genreIds) {
        if(genreIds == null) {
            return new ArrayList<>();
        }

        List<String> genreString = new ArrayList<>();
        for (int genreId : genreIds) {
            GameGenres genre = GameGenres.getGameGenreById(genreId);
            assert genre != null;
            genreString.add(genre.getName());
        }
        return genreString;
    }

    public static VideogameGetDto fromEntityToGetDto(Videogame videogame) {
        return new VideogameGetDto(
                videogame.getIgdbId(),
                videogame.getName(),
                videogame.getReleaseDate(),
                videogame.getRating(),
                convertFromListGenreIdsToListGenreString(videogame.getGenreIds()),
                videogame.isFromIGDB(),
                videogame.getSimilarGames());
    }
}