package com.mygdx.game.gameEngine.entity;

import com.mygdx.game.gameLayer.entity.EntityType;

public interface Factory {

    // Methods for creating game entities
    public Entity createEntity(int entityTypeKey, int quantity);

}