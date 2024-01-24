package com.mindera.HelloMam.enums;

public enum MediaType {
    MUSIC(1),
    MOVIE(2),
    GAME(3);

    private final int value;

    MediaType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
