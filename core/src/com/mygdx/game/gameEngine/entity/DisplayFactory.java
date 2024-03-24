package com.mygdx.game.gameEngine.entity;

import com.mygdx.game.gameLayer.entity.EntityType;

public class DisplayFactory implements GameFactory {
    @Override
    public DisplayElement createDisplayElement() {
        // Implement creation of UI elements/non-collidable entities
        return new DisplayElement();
    }
    @Override
    public Entity createEntity(EntityType entityType) {
        // DisplayFactory does not create in-game entities, return null or throw exception
        return null;
    }
}