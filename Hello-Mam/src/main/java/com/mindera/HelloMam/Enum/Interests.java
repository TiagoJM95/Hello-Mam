package com.mindera.HelloMam.Enum;

public enum Interests {
    MUSIC(1),
    MOVIE(2),
    GAME(3);

    private final int value;

    Interests(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
