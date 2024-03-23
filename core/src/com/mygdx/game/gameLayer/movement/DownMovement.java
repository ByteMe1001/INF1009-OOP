package com.mygdx.game.gameLayer.movement;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.util.iAiMovement;

public class DownMovement implements AIMovementStrategy {
    public void move(iAiMovement entity) {
        float newY = entity.getY() - entity.getSpeed() * Gdx.graphics.getDeltaTime();
        if (newY > 0) { // Check if the new position is greater than 0 (bottom screen boundary)
            entity.setY(newY);
        } else {
            // If the new position is outside the bottom boundary, set the position to the boundary
            entity.setY(0);
        }
    }
}
