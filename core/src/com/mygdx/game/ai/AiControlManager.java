package com.mygdx.game.ai;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

import java.util.Random;

public class AiControlManager {
    private EntityManager entityManager;
    private Random random = new Random();
    private static final int DEFAULT_CHANGE_RATE = 30;

    public AiControlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setLeftRight(Entity entity) {
        float x = entity.getX();
        float width = entity.getWidth();
        if (entityManager.getChangeRate(entity) <= 0) {
            int randomNumber = random.nextInt(2);
            entityManager.setCurrentDirection(entity, randomNumber == 0 ? "LEFT" : "RIGHT");
            entityManager.setChangeRate(entity, DEFAULT_CHANGE_RATE);
        }

        if ("LEFT".equals(entityManager.getCurrentDirection(entity))) {
            moveLeft(entity, x);
        } else {
            moveRight(entity, x, width);
        }
        entityManager.decrementChangeRate(entity);
    }

    public void setUpDown(Entity entity) {
        float y = entity.getY();
        float height = entity.getHeight();
        if (entityManager.getChangeRate(entity) <= 0) {
            int randomNumber = random.nextInt(2);
            entityManager.setCurrentDirection(entity, randomNumber == 0 ? "UP" : "DOWN");
            entityManager.setChangeRate(entity, DEFAULT_CHANGE_RATE);
        }

        if ("UP".equals(entityManager.getCurrentDirection(entity))) {
            moveUp(entity, y, height);
        } else {
            moveDown(entity, y);
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
                    entityManager.setCurrentDirection(entity, "LEFT");
                    break;
                case 1:
                    entityManager.setCurrentDirection(entity, "RIGHT");
                    break;
                case 2:
                    entityManager.setCurrentDirection(entity, "UP");
                    break;
                case 3:
                    entityManager.setCurrentDirection(entity, "DOWN");
                    break;
            }
            entityManager.setChangeRate(entity, DEFAULT_CHANGE_RATE);
          //  System.out.println("Entity " + entity.getId() + " New Direction: " + entityManager.getCurrentDirection(entity));
        } 

        // Apply movement based on the current direction
        switch (entityManager.getCurrentDirection(entity)) {
            case "LEFT":
                moveLeft(entity, x);
                break;
            case "RIGHT":
                moveRight(entity, x, width);
                break;
            case "UP":
                moveUp(entity, y, height);
                break;
            case "DOWN":
                moveDown(entity, y);
                break;
        }

        entityManager.decrementChangeRate(entity);
    }

    private void moveLeft(Entity entity, float x) {
        float speed = entityManager.getSpeed(entity);
        float newX = x - speed * Gdx.graphics.getDeltaTime();
        newX = Math.max(newX, 0);
        if (newX == 0) {
            entityManager.setCurrentDirection(entity, "RIGHT"); // Change direction if it hits the left boundary
        }
       // System.out.println("Entity " + entity.getId() + " Moving Left to X: " + newX);
        entity.setX(newX);
    }

    private void moveRight(Entity entity, float x, float width) {
        float speed = entityManager.getSpeed(entity);
        float newX = x + speed * Gdx.graphics.getDeltaTime();
        float rightBoundary = Gdx.graphics.getWidth() - width;
        newX = Math.min(newX, rightBoundary);
        if (newX == rightBoundary) {
            entityManager.setCurrentDirection(entity, "LEFT"); // Change direction if it hits the right boundary
        }
      //  System.out.println("Entity " + entity.getId() + " Moving Right to X: " + newX);
        entity.setX(newX);
    }

    private void moveUp(Entity entity, float y, float height) {
        float speed = entityManager.getSpeed(entity);
        float newY = y + speed * Gdx.graphics.getDeltaTime();
        float topBoundary = Gdx.graphics.getHeight() - height;
        newY = Math.min(newY, topBoundary);
        if (newY == topBoundary) {
            entityManager.setCurrentDirection(entity, "DOWN"); // Change direction if it hits the top boundary
        }
      //  System.out.println("Entity " + entity.getId() + " Moving Up to Y: " + newY);
        entity.setY(newY);
    }

    private void moveDown(Entity entity, float y) {
        float speed = entityManager.getSpeed(entity);
        float newY = y - speed * Gdx.graphics.getDeltaTime();
        newY = Math.max(newY, 0);
        if (newY == 0) {
            entityManager.setCurrentDirection(entity, "UP"); // Change direction if it hits the bottom boundary
        }
       // System.out.println("Entity " + entity.getId() + " Moving Down to Y: " + newY);
        entity.setY(newY);
    }

}
