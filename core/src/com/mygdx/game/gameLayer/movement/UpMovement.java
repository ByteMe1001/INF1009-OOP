package com.mygdx.game.gameLayer.movement;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.util.iAiMovement;

public class UpMovement implements AIMovementStrategy{
    public void move(iAiMovement entity) {
        float newY = entity.getY() + entity.getSpeed() * Gdx.graphics.getDeltaTime();
        float topBoundary = Gdx.graphics.getWidth() - entity.getHeight(); // Calculate the top boundary
        if (newY < topBoundary) { // Check if the new position is within the top screen boundary
            entity.setY(newY);
        } else {
            // If the new position is outside the top boundary, set the position to the boundary
            entity.setY(topBoundary);
        }
    }

}
