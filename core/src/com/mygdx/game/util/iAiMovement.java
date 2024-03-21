package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

import java.util.Random;

public interface iAiMovement {

    public void setLeftRight();
    public void setUpDown();
    public void setAll();
//    public void up();
//    public void down();
//    public void left();
//    public void right();
    public float getX();
    public float getY();
    public float getSpeed();
    public int getMovementSetID();
    public void movement(float[] vector);
    public int getChangeRate();
    public void setChangeRate(int i);
    public int getDefaultChangeRate();

    // Method to be implemented for moving an entity.
  Random random = new Random();

    // For screen boundary calc
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();

//    default void setLeftRight(EntityManager entityManager, Entity entity, float x, float width) {
//        if (entityManager.getChangeRate(entity) <= 0) {
//            int randomNumber = random.nextInt(2);
//                switch (randomNumber) {
//                    case 0:
//                        left(entityManager, entity, x);
//                        entityManager.setCurrentDirection(entity, "LEFT");
//                        break;
//                    case 1:
//                        right(entityManager, entity, x, width);
//                        entityManager.setCurrentDirection(entity, "RIGHT");
//                        break;
//                    default:
//                        break;
//                }
//        }
//        else {
//            switch(entityManager.getCurrentDirection(entity)) {
//                case "LEFT":
//                    left(entityManager, entity, x);
//                    break;
//                case "RIGHT":
//                    right(entityManager, entity, x, width);
//                    break;
//                default:
//                    break;
//            }
//        }
//        entityManager.decrementChangeRate(entity);
//    }
//
//    default void setUpDown(EntityManager entityManager, Entity entity, float y, float height) {
//        if (entityManager.getChangeRate(entity) <= 0) {
//            int randomNumber = random.nextInt(2);
//            switch (randomNumber) {
//                case 0:
//                    up(entityManager, entity, y, height);
//                    entityManager.setCurrentDirection(entity, "UP");
//                    break;
//                case 1:
//                    down(entityManager, entity, y);
//                    entityManager.setCurrentDirection(entity, "DOWN");
//                    break;
//                default:
//                    break;
//            }
//        } else {
//            switch (entityManager.getCurrentDirection(entity)) {
//                case "UP":
//                    up(entityManager, entity, y, height);
//                    break;
//                case "DOWN":
//                    down(entityManager, entity, y);
//                    break;
//                default:
//                    break;
//            }
//        }
//        entityManager.decrementChangeRate(entity);
//    }
//
//
//    default void setAll(EntityManager entityManager, Entity entity, float x, float y, float width, float height) {
//        if (entityManager.getChangeRate(entity) <= 0) {
//            int randomNumber = random.nextInt(4); // Random number between 0 and 3 for four directions
//            switch (randomNumber) {
//                case 0:
//                    left(entityManager, entity, x);
//                    entityManager.setCurrentDirection(entity, "LEFT");
//                    break;
//                case 1:
//                    right(entityManager, entity, x, width);
//                    entityManager.setCurrentDirection(entity, "RIGHT");
//                    break;
//                case 2:
//                    up(entityManager, entity, y, height);
//                    entityManager.setCurrentDirection(entity, "UP");
//                    break;
//                case 3:
//                    down(entityManager, entity, y);
//                    entityManager.setCurrentDirection(entity, "DOWN");
//                    break;
//                default:
//                    break;
//            }
//        } else {
//            switch(entityManager.getCurrentDirection(entity)) {
//                case "LEFT":
//                    left(entityManager, entity, x);
//                    break;
//                case "RIGHT":
//                    right(entityManager, entity, x, width);
//                    break;
//                case "UP":
//                    up(entityManager, entity, y, height);
//                    break;
//                case "DOWN":
//                    down(entityManager, entity, y);
//                    break;
//                default:
//                    break;
//            }
//        }
//        entityManager.decrementChangeRate(entity);
//    }
//
//
//    default void left(EntityManager entityManager, Entity entity, float x) {
//        float newX = x - entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
//        if (newX > 0) { // Check if the new position is within the left screen boundary
//            entityManager.setX(entity, newX);
//        } else {
//            // If the new position is outside the left boundary, set the position to the boundary
//            entityManager.setX(entity, 0);
//        }
//    }
//
//    default void right(EntityManager entityManager, Entity entity, float x, float width) {
//        // Calculate the new x-coordinate based on the entity's speed and delta time
//        float newX = x + entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
//
//        // Calculate the right boundary as the screen width minus the entity's width
//        float rightBoundary = screenWidth - width;
//
//        if (newX <= rightBoundary) { // Check if the new position is within the right screen boundary
//            entityManager.setX(entity, newX);
//        } else {
//            // If the new position is outside the right boundary, set the position to the boundary
//            entityManager.setX(entity, rightBoundary);
//        }
//    }
//
//    default void up(EntityManager entityManager, Entity entity, float y, float height) {
//        float newY = y + entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
//        float topBoundary = screenHeight - height; // Calculate the top boundary
//
//        if (newY < topBoundary) { // Check if the new position is within the top screen boundary
//            entityManager.setY(entity, newY);
//        } else {
//            // If the new position is outside the top boundary, set the position to the boundary
//            entityManager.setY(entity, topBoundary);
//        }
//    }
//
//    default void down(EntityManager entityManager, Entity entity, float y) {
//        float newY = y - entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
//        if (newY > 0) { // Check if the new position is greater than 0 (bottom screen boundary)
//            entityManager.setY(entity, newY);
//        } else {
//            // If the new position is outside the bottom boundary, set the position to the boundary
//            entityManager.setY(entity, 0);
//        }
//    }


}
