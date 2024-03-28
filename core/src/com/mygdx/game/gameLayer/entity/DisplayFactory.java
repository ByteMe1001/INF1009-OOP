package com.mygdx.game.gameLayer.entity;

import com.mygdx.game.gameEngine.entity.DisplayElement;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.Factory;

public class DisplayFactory implements Factory {
    @Override
    public DisplayElement createDisplayElement() {
        // Implement creation of UI elements/non-collidable entities
        return new DisplayElement();
    }
    @Override
    public Entity createEntity(int i, int quantity) {
        // DisplayFactory does not create in-game entities, return null or throw exception
        return null;
    }
}