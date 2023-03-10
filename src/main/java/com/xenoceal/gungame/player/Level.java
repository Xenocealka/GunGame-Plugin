package com.xenoceal.gungame.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Level {

    FIRST(1, 0),
    SECOND(2, 12),
    THIRD(3, 36),
    FOURTH(4, 60),
    FIFTH(5, 84),
    SIXTH(6, 108),
    SEVENTH(7, 132),
    EIGHTH(8, 156),
    NINTH(9, 180),
    TENTH(10, 204),
    ELEVEN(11, 228);

    @Getter
    private final int value;
    @Getter
    private final int required;

}
