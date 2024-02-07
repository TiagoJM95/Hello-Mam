package com.mindera.converters;

import com.mindera.dtos.GameGetDto;
import com.mindera.entities.Game;

import static com.mindera.util.Utility.convertDate;
import static com.mindera.util.Utility.convertFromListGenreIdsToListGenreString;

public class GameConverter {

    public static GameGetDto fromEntityToGetDto(Game game) {
        return new GameGetDto(
                game.getIgdbId(),
                game.getName(),
                convertDate(game.getReleaseDate()),
                game.getRating(),
                convertFromListGenreIdsToListGenreString(game.getGenreIds()),
                game.isFromIGDB(),
                game.getSimilarGames());
    }
}