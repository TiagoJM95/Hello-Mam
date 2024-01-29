package com.mindera.HelloMam.enums;

import lombok.Getter;

@Getter
public enum MediaType {
    MOVIE("movie"),
    GAME("game"),
    MUSIC("music");

    final String description;

    MediaType(String description){
        this.description = description;
    }
}