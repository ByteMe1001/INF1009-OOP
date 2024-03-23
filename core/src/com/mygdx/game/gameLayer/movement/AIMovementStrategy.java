package com.mygdx.game.gameLayer.movement;

import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.util.iAiMovement;

public interface AIMovementStrategy {
    void move(iAiMovement entity);
}
