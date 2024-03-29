package com.mygdx.game.gameLayer.movement;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.player.PlayerMovement;
import com.mygdx.game.gameEngine.util.iPlayerMovement;

public class Player1MovementStrategy extends PlayerMovement {

    public float[] calculateMovement(iPlayerMovement entity, String direction) {
        switch(direction) {
            case "UP":
                return up(entity);
            case "DOWN":
                return down(entity);
            case "LEFT" :
                return left(entity);
            case "RIGHT" :
                return right(entity);
            default:
                return noMovement(entity);
        }
    }

    public float[] up(iPlayerMovement entity) {
        float [] vector = new float[2];
        vector[0] = entity.getX();
        float newY = entity.getY() + entity.getSpeed() * Gdx.graphics.getDeltaTime();
        float topBoundary = Gdx.graphics.getHeight() - entity.getSpriteHeight();
        if (newY < topBoundary) { // Check if the new position is within the top screen boundary
            vector[1] = (newY);
        } else {
            // If the new position is outside the top boundary, set the position to the boundary
            vector[1] = (topBoundary);
        }
        return vector;
    }

    public float[] down(iPlayerMovement entity) {
        float[] vector = new float[2];
        vector[0] = entity.getX();
        float newY = entity.getY() - entity.getSpeed() * Gdx.graphics.getDeltaTime();
        float bottomBoundary = 0; // Assuming the bottom of the screen is at y-coordinate 0

        if (newY > bottomBoundary) { // Check if the new position is within the bottom screen boundary
            vector[1] = newY;
        } else {
            // If the new position is outside the bottom boundary, set the position to the boundary
            vector[1] = bottomBoundary;
        }
        return vector;
    }

    public float[] left(iPlayerMovement entity) {
        float[] vector = new float[2];
        vector[1] = entity.getY();
        float newX = entity.getX() - entity.getSpeed() * Gdx.graphics.getDeltaTime();
        float leftBoundary = 0; // Assuming the left edge of the screen is at x-coordinate 0

        if (newX > leftBoundary) { // Check if the new position is within the left screen boundary
            vector[0] = newX;
        } else {
            // If the new position is outside the left boundary, set the position to the boundary
            vector[0] = leftBoundary;
        }
        return vector;
    }

    public float[] right(iPlayerMovement entity) {
        float[] vector = new float[2];
        vector[1] = entity.getY();
        float newX = entity.getX() + entity.getSpeed() * Gdx.graphics.getDeltaTime();
        float rightBoundary = Gdx.graphics.getWidth() - entity.getSpriteWidth(); // Calculate the right boundary

        if (newX < rightBoundary) { // Check if the new position is within the right screen boundary
            vector[0] = newX;
        } else {
            // If the new position is outside the right boundary, set the position to the boundary
            vector[0] = rightBoundary;

        }
        return vector;
    }

    public float[] noMovement(iPlayerMovement entity) {
        float[] vector = new float[2];
        vector[0] = entity.getX();
        vector[1] = entity.getY();
        return vector;
    }

}
