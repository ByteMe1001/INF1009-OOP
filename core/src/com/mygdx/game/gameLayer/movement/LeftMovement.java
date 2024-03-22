package com.mygdx.game.gameLayer.movement;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.util.iAiMovement;

public class LeftMovement implements AIMovementStrategy {
    public void move(iAiMovement entity) {
        float newX = entity.getX() - entity.getSpeed() * Gdx.graphics.getDeltaTime();
        if (newX > 0) { // Check if the new position is within the left screen boundary
            entity.setX(newX);
        } else {
            // If the new position is outside the left boundary, set the position to the boundary
            entity.setX(0);
        }
    }
}
