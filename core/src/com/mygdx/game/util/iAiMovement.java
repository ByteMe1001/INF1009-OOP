package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

import java.util.Random;

public interface iAiMovement {
    // Method to be implemented for moving an entity.
    Random random = new Random();

    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();

    default void setLeftRight(EntityManager entityManager, Entity entity) {
        if (entityManager.getChangeRate(entity) <= 0) {
            int randomNumber = random.nextInt(2);
                switch (randomNumber) {
                    case 0:
                        left(entityManager, entity);
                        entityManager.setCurrentDirection(entity, "LEFT");
                        break;
                    case 1:
                        right(entityManager, entity);
                        entityManager.setCurrentDirection(entity, "RIGHT");
                        break;
                    default:
                        break;
                }
        }
        else {
            switch(entityManager.getCurrentDirection(entity)) {
                case "LEFT":
                    left(entityManager, entity);
                    break;
                case "RIGHT":
                    right(entityManager, entity);
                    break;
                default:
                    break;
            }
        }
        entityManager.decrementChangeRate(entity);
    }

    default void setUpDown(EntityManager entityManager, Entity entity) {

        int randomNumber = random.nextInt(3) - 1;

        entityManager.setY(entity, (entityManager.getX(entity) + randomNumber * entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime()));
        entityManager.decrementChangeRate(entity);
    }

    default void setAll(EntityManager entityManager, Entity entity) {

        int randomNumber = random.nextInt(3) - 1;

        entityManager.setX(entity, (entityManager.getX(entity) + randomNumber * entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime()));

        int randomNumber2 = random.nextInt(3) - 1;
        entityManager.setY(entity, (entityManager.getY(entity) + randomNumber2 * entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime()));
        entityManager.decrementChangeRate(entity);
    }

    default void left(EntityManager entityManager, Entity entity) {
        float newX = entityManager.getX(entity) - entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
        if (newX > 0) { // Check if the new position is within the left screen boundary
            entityManager.setX(entity, newX);
        } else {
            // If the new position is outside the left boundary, set the position to the boundary
            entityManager.setX(entity, 0);
        }
    }

    default void right(EntityManager entityManager, Entity entity) {
        float newX = entityManager.getX(entity) + entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
        if (newX < screenWidth) { // Check if the new position is within the right screen boundary
            entityManager.setX(entity, newX);
        } else {
            // If the new position is outside the right boundary, set the position to the boundary
            entityManager.setX(entity, screenWidth);
        }
    }

    default void up(EntityManager entityManager, Entity entity) {
        float newY = entityManager.getY(entity) + entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
        if (newY < screenHeight) { // Check if the new position is within the top screen boundary
            entityManager.setY(entity, newY);
        } else {
            // If the new position is outside the top boundary, set the position to the boundary
            entityManager.setY(entity, screenHeight);
        }
    }

    default void down(EntityManager entityManager, Entity entity) {
        float newY = entityManager.getY(entity) - entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
        if (newY > 0) { // Check if the new position is greater than 0 (bottom screen boundary)
            entityManager.setY(entity, newY);
        } else {
            // If the new position is outside the bottom boundary, set the position to the boundary
            entityManager.setY(entity, 0);
        }
    }
}
