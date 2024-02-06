package com.mindera.HelloMam.enums;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum MediaType {
    MOVIE("movie"),
    GAME("game");

    final String description;

    MediaType(String description){
        this.description = description;
    }

    public static Optional<MediaType> getTypeByDescription(String description) {
        for(MediaType mediaType : values()) {
            if(mediaType.getDescription().equalsIgnoreCase(description)) {
                return Optional.of(mediaType);
            }
        }
        return Optional.empty();
    }
}