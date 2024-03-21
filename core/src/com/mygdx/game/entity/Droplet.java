package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ai.AiControlManager;
import com.mygdx.game.util.iAiMovement;

import java.util.Random;

public class Droplet extends CollidableEntities implements iAiMovement{

    // Additional properties for Droplet class
    private final static String TEXTURE_PATH = "asteroid.png";
    private static final int DEFAULT_CHANGE_RATE = 30;
    private int changeRate;
    private AiControlManager aiControlManager;
    private SpriteBatch batch;

    // For screen boundary calc
    private int screenWidth = Gdx.graphics.getWidth();
    private int screenHeight = Gdx.graphics.getHeight();

    // Default constructor
    public Droplet() {
        // do nothing don't touch
    }
    // Constructor with id parameter
    public Droplet(int id, SpriteBatch batch) {
        super(id, batch);
        this.setChangeRate(DropletType.DEFAULT.getChangeRate());
        this.setSprite(new Sprite(new Texture(DropletType.DEFAULT.getTexturePath())));
        this.setAlive(DropletType.DEFAULT.isAlive());
        this.setCollidable(DropletType.DEFAULT.isCollidable());
        this.setControl(DropletType.DEFAULT.getControl());
    }

    // Parameterized constructor
    public Droplet(AiControlManager aiControlManager, int id, int health, float x, float y, float scale, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture(DropletType.DEFAULT.getTexturePath())), width, height, speed, direction, batch);
        setWidth(getSprite().getWidth());
        setHeight(getSprite().getHeight());
        this.aiControlManager = aiControlManager;
        this.setChangeRate(DropletType.DEFAULT.getChangeRate());
        this.setAlive(DropletType.DEFAULT.isAlive());
        this.setCollidable(DropletType.DEFAULT.isCollidable());
        this.setControl(DropletType.DEFAULT.getControl());
    }

    // Getter and setter methods
    @Override
    public void update() {
        movement();
        super.getBoundingBox().setPosition(getX(), getY());
    }

    public int getChangeRate() {
        return changeRate;
    } 


	public int getDefaultChangeRate() {
        return DEFAULT_CHANGE_RATE;
    } 

    @Override
    public void setChangeRate(int changeRate) {
        this.changeRate = changeRate;
    }

    public void decrementChangeRate() {
        this.changeRate--;
    }


    // Game design logic
    
    public void takeDamage(int damage) {
        // Handle damage logic for the droplet
    }

    @Override
    public void heal(int amount) {
        // Handle healing logic for the droplet
    }

    @Override
    public void destroy() {
        // Handle destruction logic for the droplet
    }

    // Movement logic
    @Override
    public void movement() {

    }

    public void movement(float[] vector) {
        if (getControl() == 'A') {
            switch (super.getMovementSetID()) {         // 1 for up down, 2 for left right, 3 for all
                case 1:
                    setY(vector[1]);    // Set Y value
                    break;
                case 2:
                    setX(vector[0]);    //Set X value
                    break;
                case 3:
                    setX(vector[0]);    //Set X value
                    setY(vector[1]);    // Set Y value
                    break;
            }
        }
    }

    public void setLeftRight() {
        // Check if entity has finished moving
        if (getChangeRate() <= 0) {
            int randomNumber = random.nextInt(2);
            switch (randomNumber) {
                case 0:
                    left();
                    setCurrentDirection("LEFT");
                    break;
                case 1:
                    right();
                    setCurrentDirection("RIGHT");
                    break;
                default:
                    break;
            }
        }
        // If still moving, continue on
        else {
            switch(getCurrentDirection()) {
                case "LEFT":
                    left();
                    break;
                case "RIGHT":
                    right();
                    break;
                default:
                    break;
            }
        }
        decrementChangeRate();
    }

    public void setUpDown() {
        // Check if entity has finished moving
        if (getChangeRate() <= 0) {
            int randomNumber = random.nextInt(2);
            switch (randomNumber) {
                case 0:
                    up();
                    setCurrentDirection("UP");
                    break;
                case 1:
                    down();
                    setCurrentDirection("DOWN");
                    break;
                default:
                    break;
            }
        // If still moving, continue on
        } else {
            switch (getCurrentDirection()) {
                case "UP":
                    up();
                    break;
                case "DOWN":
                    down();
                    break;
                default:
                    break;
            }
        }
        decrementChangeRate();
    }

    public void setAll() {
        // Check if entity has finished moving
        if (getChangeRate() <= 0) {
            int randomNumber = random.nextInt(4); // Random number between 0 and 3 for four directions
            switch (randomNumber) {
                case 0:
                    left();
                    setCurrentDirection("LEFT");
                    break;
                case 1:
                    right();
                    setCurrentDirection("RIGHT");
                    break;
                case 2:
                    up();
                    setCurrentDirection("UP");
                    break;
                case 3:
                    down();
                    setCurrentDirection("DOWN");
                    break;
                default:
                    break;
            }
        // If still moving, continue on
        } else {
            switch(getCurrentDirection()) {
                case "LEFT":
                    left();
                    break;
                case "RIGHT":
                    right();
                    break;
                case "UP":
                    up();
                    break;
                case "DOWN":
                    down();
                    break;
                default:
                    break;
            }
        }
        decrementChangeRate();
    }

    public void left() {
        float newX = getX() - getSpeed() * Gdx.graphics.getDeltaTime();
        if (newX > 0) { // Check if the new position is within the left screen boundary
            setX(newX);
        } else {
            // If the new position is outside the left boundary, set the position to the boundary
            setX(0);
        }
    }

    public void right() {
        // Calculate the new x-coordinate based on the entity's speed and delta time
        float newX = getX() + getSpeed() * Gdx.graphics.getDeltaTime();

        // Calculate the right boundary as the screen width minus the entity's width
        float rightBoundary = Gdx.graphics.getWidth() - getWidth();

        if (newX <= rightBoundary) { // Check if the new position is within the right screen boundary
            setX(newX);
        } else {
            // If the new position is outside the right boundary, set the position to the boundary
            setX(rightBoundary);
        }
    }

    public void up() {
        float newY = getY() + getSpeed() * Gdx.graphics.getDeltaTime();
        float topBoundary = Gdx.graphics.getWidth() - getHeight(); // Calculate the top boundary
        if (newY < topBoundary) { // Check if the new position is within the top screen boundary
            setY(newY);
        } else {
            // If the new position is outside the top boundary, set the position to the boundary
            setY(topBoundary);
        }
    }

    public void down() {
        float newY = getY() - getSpeed() * Gdx.graphics.getDeltaTime();
        if (newY > 0) { // Check if the new position is greater than 0 (bottom screen boundary)
            setY(newY);
        } else {
            // If the new position is outside the bottom boundary, set the position to the boundary
            setY(0);
        }
    }
}
