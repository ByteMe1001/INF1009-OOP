package com.mygdx.game.gameLayer.movement;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.util.iAiMovement;

public class RightMovement implements AIMovementStrategy{
    public void move(iAiMovement entity) {
        // Calculate the new x-coordinate based on the entity's speed and delta time
        float newX = entity.getX() + entity.getSpeed() * Gdx.graphics.getDeltaTime();

        // Calculate the right boundary as the screen width minus the entity's width
        float rightBoundary = Gdx.graphics.getWidth() - entity.getSpriteWidth();

        if (newX <= rightBoundary) { // Check if the new position is within the right screen boundary
            entity.setX(newX);
        } else {
            // If the new position is outside the right boundary, set the position to the boundary
            entity.setX(rightBoundary);
        }
    }
}
