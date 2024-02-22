package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

import java.util.Random;

public interface iAiMovement {
    // Method to be implemented for moving an entity.
    Random random = new Random();

    default void calculateLeftRight(EntityManager entityManager, Entity entity) {

        int randomNumber = random.nextInt(3) - 1;

        entityManager.setX(entity, (entityManager.getX(entity) + randomNumber * entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime()));
        entityManager.decrementChangeRate(entity);
    }

    default void calculateUpDown(EntityManager entityManager, Entity entity) {

        int randomNumber = random.nextInt(3) - 1;

        entityManager.setY(entity, (entityManager.getX(entity) + randomNumber * entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime()));
        entityManager.decrementChangeRate(entity);
    }

    default void calculateAll(EntityManager entityManager, Entity entity) {

        int randomNumber = random.nextInt(3) - 1;

        entityManager.setX(entity, (entityManager.getX(entity) + randomNumber * entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime()));

        int randomNumber2 = random.nextInt(3) - 1;
        entityManager.setY(entity, (entityManager.getY(entity) + randomNumber2 * entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime()));
        entityManager.decrementChangeRate(entity);
    }
}
