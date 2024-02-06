package com.mindera.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor

public enum GameGenres {
    FIGHTING(4, "Fighting"),
    SHOOTER(5, "Shooter"),
    MUSIC(7, "Music"),
    PLATFORM(8, "Platform"),
    PUZZLE(9, "Puzzle"),
    RACING(10, "Racing"),
    RTS(11, "Real Time Strategy"),
    RPG(12, "RPG"),
    SIMULATOR(13, "Simulator"),
    SPORT(14, "Sport"),
    STRATEGY(15, "Strategy"),
    TBS(16, "Turn based"),
    TACTICAL(24, "Tactical"),
    QUIZ_TRIVIA(26, "Quiz"),
    HACK_SLASH_BEAT_EM_UP(25, "Hack and Slash"),
    PINBALL(30, "Pinball"),
    ADVENTURE(31, "Adventure"),
    ARCADE(33, "Arcade"),
    VISUAL_NOVEL(34, "Visual Novel"),
    INDIE(32, "Indie"),
    CARD_BOARD_GAME(35, "Card and Board"),
    MOBA(36, "MOBA"),
    POINT_AND_CLICK(2, "Point and Click"),;

    private final int id;
    private final String name;

    public static Optional<GameGenres> getGameGenreByName(String name) {
        for (GameGenres gameGenres : GameGenres.values()) {
            if (gameGenres.getName().replace(" ", "").equalsIgnoreCase(name)) {
                return Optional.of(gameGenres);
            }
        }
        return Optional.empty();
    }

    public static GameGenres getGameGenreById(int id) {
        for (GameGenres gameGenres : GameGenres.values()) {
            if (gameGenres.getId() == id) {
                return gameGenres;
            }
        }
        return null;
    }
}