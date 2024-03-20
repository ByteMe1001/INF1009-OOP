package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ai.AiControlManager;
import com.mygdx.game.util.iAiMovement;

import java.util.Random;

public class Droplet extends CollidableEntities {

    // Additional properties for Droplet class
    private final static String TEXTURE_PATH = "asteroid.png";
    //private static final int DEFAULT_CHANGE_RATE = 30;
   // private int changeRate;
    private AiControlManager aiControlManager;
    private SpriteBatch batch;

    // Default constructor
    protected Droplet() {
        // do nothing don't touch
    }
    // Constructor with id parameter
    protected Droplet(int id, SpriteBatch batch) {
        super(id, batch);
       // this.setChangeRate(DropletType.DEFAULT.getChangeRate());
        this.setSprite(new Sprite(new Texture(DropletType.DEFAULT.getTexturePath())));
        this.setAlive(DropletType.DEFAULT.isAlive());
        this.setCollidable(DropletType.DEFAULT.isCollidable());
        this.setControl(DropletType.DEFAULT.getControl());
    }

    // Parameterized constructor
    protected Droplet(AiControlManager aiControlManager, int id, int health, float x, float y, float scale, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture(DropletType.DEFAULT.getTexturePath())), width, height, speed, direction, batch);
        setWidth(getSprite().getWidth());
        setHeight(getSprite().getHeight());
        this.aiControlManager = aiControlManager;
       // this.setChangeRate(DropletType.DEFAULT.getChangeRate());
        this.setAlive(DropletType.DEFAULT.isAlive());
        this.setCollidable(DropletType.DEFAULT.isCollidable());
        this.setControl(DropletType.DEFAULT.getControl());
        Random random = new Random();
        String initialDirection = random.nextBoolean() ? "UP" : "DOWN";
        this.setCurrentDirection(initialDirection);
    }

    // Getter and setter methods
    @Override
    protected void update() {
        movement();
        super.getBoundingBox().setPosition(getX(), getY());
    }

    @Override
    /*protected int getChangeRate() {
        return changeRate;
    } 

    @Override
	public int getDefaultChangeRate() {
        return DEFAULT_CHANGE_RATE;
    } 

    @Override
    protected void setChangeRate(int changeRate) {
        this.changeRate = changeRate;
    } */

    // Game design logic
    
    protected void takeDamage(int damage) {
        // Handle damage logic for the droplet
    }

    @Override
    protected void heal(int amount) {
        // Handle healing logic for the droplet
    }

    @Override
    protected void destroy() {
        // Handle destruction logic for the droplet
    }
   
    
    private Random random = new Random();
    
    
    private int movementPreference = AiControlManager.MOVE_UP_DOWN;
    
    public int getMovementPreference() {
        return movementPreference;
    }

    // Movement logic
    @Override
    public void movement() {
        if (getControl() == 'A') {
            aiControlManager.movement(this, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getMovementPreference());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    // For screen boundary calc
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();

    public void setLeftRight(EntityManager entityManager, Entity entity, float x, float width) {
        if (entityManager.getChangeRate(entity) <= 0) {
            int randomNumber = random.nextInt(2);
                switch (randomNumber) {
                    case 0:
                        left(entityManager, entity, x);
                        entityManager.setCurrentDirection(entity, "LEFT");
                        break;
                    case 1:
                        right(entityManager, entity, x, width);
                        entityManager.setCurrentDirection(entity, "RIGHT");
                        break;
                    default:
                        break;
                }
        }
        else {
            switch(entityManager.getCurrentDirection(entity)) {
                case "LEFT":
                    left(entityManager, entity, x);
                    break;
                case "RIGHT":
                    right(entityManager, entity, x, width);
                    break;
                default:
                    break;
            }
        }
        entityManager.decrementChangeRate(entity);
    }

    public void setUpDown(EntityManager entityManager, Entity entity, float y, float height) {
        if (entityManager.getChangeRate(entity) <= 0) {
            int randomNumber = random.nextInt(2);
            switch (randomNumber) {
                case 0:
                    up(entityManager, entity, y, height);
                    entityManager.setCurrentDirection(entity, "UP");
                    break;
                case 1:
                    down(entityManager, entity, y);
                    entityManager.setCurrentDirection(entity, "DOWN");
                    break;
                default:
                    break;
            }
        } else {
            switch (entityManager.getCurrentDirection(entity)) {
                case "UP":
                    up(entityManager, entity, y, height);
                    break;
                case "DOWN":
                    down(entityManager, entity, y);
                    break;
                default:
                    break;
            }
        }
        entityManager.decrementChangeRate(entity);
    }


    public void setAll(EntityManager entityManager, Entity entity, float x, float y, float width, float height) {
        if (entityManager.getChangeRate(entity) <= 0) {
            int randomNumber = random.nextInt(4); // Random number between 0 and 3 for four directions
            switch (randomNumber) {
                case 0:
                    left(entityManager, entity, x);
                    entityManager.setCurrentDirection(entity, "LEFT");
                    break;
                case 1:
                    right(entityManager, entity, x, width);
                    entityManager.setCurrentDirection(entity, "RIGHT");
                    break;
                case 2:
                    up(entityManager, entity, y, height);
                    entityManager.setCurrentDirection(entity, "UP");
                    break;
                case 3:
                    down(entityManager, entity, y);
                    entityManager.setCurrentDirection(entity, "DOWN");
                    break;
                default:
                    break;
            }
        } else {
            switch(entityManager.getCurrentDirection(entity)) {
                case "LEFT":
                    left(entityManager, entity, x);
                    break;
                case "RIGHT":
                    right(entityManager, entity, x, width);
                    break;
                case "UP":
                    up(entityManager, entity, y, height);
                    break;
                case "DOWN":
                    down(entityManager, entity, y);
                    break;
                default:
                    break;
            }
        }
        entityManager.decrementChangeRate(entity);
    }


    public void left(EntityManager entityManager, Entity entity, float x) {
        float newX = x - entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
        if (newX > 0) { // Check if the new position is within the left screen boundary
            entityManager.setX(entity, newX);
        } else {
            // If the new position is outside the left boundary, set the position to the boundary
            entityManager.setX(entity, 0);
        }
    }

    public void right(EntityManager entityManager, Entity entity, float x, float width) {
        // Calculate the new x-coordinate based on the entity's speed and delta time
        float newX = x + entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();

        // Calculate the right boundary as the screen width minus the entity's width
        float rightBoundary = screenWidth - width;

        if (newX <= rightBoundary) { // Check if the new position is within the right screen boundary
            entityManager.setX(entity, newX);
        } else {
            // If the new position is outside the right boundary, set the position to the boundary
            entityManager.setX(entity, rightBoundary);
        }
    }

    public void up(EntityManager entityManager, Entity entity, float y, float height) {
        if (!"UP".equals(entityManager.getCurrentDirection(entity))) return;

        float newY = y + entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();
        float topBoundary = screenHeight - height;

        if (newY >= topBoundary) {
            entityManager.setCurrentDirection(entity, "DOWN"); // Change direction at the boundary
            // Adjust position slightly off the boundary to ensure visible movement in the new direction
            newY = topBoundary - 1;
        }

        entityManager.setY(entity, newY);
    }

    public void down(EntityManager entityManager, Entity entity, float y) {
        if (!"DOWN".equals(entityManager.getCurrentDirection(entity))) return;

        float newY = y - entityManager.getSpeed(entity) * Gdx.graphics.getDeltaTime();

        if (newY <= 0) {
            entityManager.setCurrentDirection(entity, "UP"); // Change direction at the boundary
            // Adjust position slightly off the boundary to ensure visible movement in the new direction
            newY = 1;
        }

        entityManager.setY(entity, newY);
    }


}
