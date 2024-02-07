package com.mindera.converters;

import com.mindera.dtos.GameGetDto;
import com.mindera.entities.Game;
import com.mindera.enums.GameGenres;
import com.mindera.util.DateConverter;

import java.util.ArrayList;
import java.util.List;

public class GameConverter {

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

    public static GameGetDto fromEntityToGetDto(Game game) {
        return new GameGetDto(
                game.getIgdbId(),
                game.getName(),
                DateConverter.convertDate(game.getReleaseDate()),
                game.getRating(),
                convertFromListGenreIdsToListGenreString(game.getGenreIds()),
                game.isFromIGDB(),
                game.getSimilarGames());
    }
}