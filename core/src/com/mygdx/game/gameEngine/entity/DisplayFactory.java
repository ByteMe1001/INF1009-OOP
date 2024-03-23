package com.mygdx.game.gameEngine.entity;

public class DisplayFactory implements GameFactory {
    @Override
    public DisplayElement createDisplayElement() {
        // Implement creation of UI elements/non-collidable entities
        return new DisplayElement();
    }
    @Override
    public Entity createEntity() {
        // DisplayFactory does not create in-game entities, return null or throw exception
        return null;
    }
}