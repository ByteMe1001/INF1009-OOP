package com.mygdx.game.gameEngine.entity;

public interface GameFactory {
    // Methods for creating UI elements
    public DisplayElement createDisplayElement();

    // Methods for creating game entities
    public Entity createEntity();


}