package com.mindera.util;

import com.mindera.enums.GameGenres;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static String convertDate(Long timestamp) {
        if(timestamp == null){
            return "";
        }
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public static List<String> convertFromListGenreIdsToListGenreString(List<Integer> genreIds) {
        List<String> genreString = new ArrayList<>();
        if(genreIds == null) {
            return genreString;
        }
        for (int genreId : genreIds) {
            GameGenres genre = GameGenres.getGameGenreById(genreId);
            assert genre != null;
            genreString.add(genre.getName());
        }
        return genreString;
    }
}