package com.mygdx.game.ai;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.util.iAiMovement;

import java.util.Random;

public class AiControlManager {
    private EntityManager entityManager;
    private Random random = new Random();

    public AiControlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setLeftRight(Entity entity) {
        float x = entity.getX();
        float width = entity.getWidth();
        if (entityManager.getChangeRate(entity) <= 0) {
            int randomNumber = random.nextInt(2);
            if (randomNumber == 0) {
                moveLeft(entity);
            } else {
                moveRight(entity, width);
            }
        } else {
            if ("LEFT".equals(entityManager.getCurrentDirection(entity))) {
                moveLeft(entity);
            } else {
                moveRight(entity, width);
            }
        }
        entityManager.decrementChangeRate(entity);
    }

    public void setUpDown(Entity entity) {
        float y = entity.getY();
        float height = entity.getHeight();
        if (entityManager.getChangeRate(entity) <= 0) {
            int randomNumber = random.nextInt(2);
            if (randomNumber == 0) {
                moveUp(entity, height);
            } else {
                moveDown(entity);
            }
        } else {
            if ("UP".equals(entityManager.getCurrentDirection(entity))) {
                moveUp(entity, height);
            } else {
                moveDown(entity);
            }
        }
        entityManager.decrementChangeRate(entity);
    }

    public void setAll(Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float width = entity.getWidth();
        float height = entity.getHeight();
        if (entityManager.getChangeRate(entity) <= 0) {
            int randomNumber = random.nextInt(4);
            switch (randomNumber) {
                case 0:
                    moveLeft(entity);
                    break;
                case 1:
                    moveRight(entity, width);
                    break;
                case 2:
                    moveUp(entity, height);
                    break;
                case 3:
                    moveDown(entity);
                    break;
            }
        } else {
            switch (entityManager.getCurrentDirection(entity)) {
                case "LEFT":
                    moveLeft(entity);
                    break;
                case "RIGHT":
                    moveRight(entity, width);
                    break;
                case "UP":
                    moveUp(entity, height);
                    break;
                case "DOWN":
                    moveDown(entity);
                    break;
            }
        }
        entityManager.decrementChangeRate(entity);
    }

    private void moveLeft(Entity entity) {
        float x = entity.getX();
        float speed = entityManager.getSpeed(entity);
        float newX = x - speed * Gdx.graphics.getDeltaTime();
        entity.setX(Math.max(newX, 0)); // Ensure the new X is not out of bounds
    }

    private void moveRight(Entity entity, float width) {
        float x = entity.getX();
        float speed = entityManager.getSpeed(entity);
        float newX = x + speed * Gdx.graphics.getDeltaTime();
        float rightBoundary = Gdx.graphics.getWidth() - width;
        entity.setX(Math.min(newX, rightBoundary)); // Ensure the new X is not out of bounds
    }

    private void moveUp(Entity entity, float height) {
        float y = entity.getY();
        float speed = entityManager.getSpeed(entity);
        float newY = y + speed * Gdx.graphics.getDeltaTime();
        float topBoundary = Gdx.graphics.getHeight() - height;
        entity.setY(Math.min(newY, topBoundary)); // Ensure the new Y is not out of bounds
    }

    private void moveDown(Entity entity) {
        float y = entity.getY();
        float speed = entityManager.getSpeed(entity);
        float newY = y - speed * Gdx.graphics.getDeltaTime();
        entity.setY(Math.max(newY, 0)); // Ensure the new Y is not out of bounds
    }
}
