package com.xenoceal.gungame.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GameState {
    WAITING("Waiting..."),
    STARTING("Starting..."),
    STARTED("Game started"),
    ENDED("Game ended");

    @Getter
    private final String text;
}
