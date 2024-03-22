package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameEngine.ai.AiControlManager;
import com.mygdx.game.gameEngine.util.iAiMovement;

public class Bullet extends CollidableEntities implements iAiMovement {

    // Texture path for the bullet
    private static final String TEXTURE_PATH = "bullet1.png";
    private static final int DEFAULT_CHANGE_RATE = 0;
    private int changeRate;

    private AiControlManager aiControlManager;

    // Default constructor
    public Bullet() {
        // Do nothing for now
    }

    // Constructor with ID parameter
    public Bullet(int id, SpriteBatch batch) {
       super (id,batch);
       this.setChangeRate(BulletType.DEFAULT.getChangeRate());
       this.setSprite(new Sprite((new Texture(BulletType.DEFAULT.getTexturePath()))));
       this.setAlive(BulletType.DEFAULT.isAlive());
       this.setCollidable(BulletType.DEFAULT.isCollidable());
       this.setControl(BulletType.DEFAULT.getControl());
    }

    // Parameterized constructor
    public Bullet(AiControlManager aiControlManager, int id, int health, float x, float y, float scale, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture(BulletType.DEFAULT.getTexturePath())), width, height, speed, direction, batch);
        setWidth(getSprite().getWidth());
        setHeight(getSprite().getHeight());
        this.aiControlManager = aiControlManager;
        this.setChangeRate(BulletType.DEFAULT.getChangeRate());
        this.setAlive(BulletType.DEFAULT.isAlive());
        this.setCollidable(BulletType.DEFAULT.isCollidable());
        this.setControl(BulletType.DEFAULT.getControl());
    }
    @Override
    public void update() {
        movement();
        super.getBoundingBox().setPosition(getX(), getY());
    }
    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public void heal(int amount) {

    }

    @Override
    public void destroy() {

    }

    public int getChangeRate() {
        return changeRate;
    }

    public int getDefaultChangeRate() {
        return DEFAULT_CHANGE_RATE;
    }

    //Movement Logic

    @Override
    public void movement() {
        // Move the bullet upwards
        float newY = getY() + getSpeed(); // Adjust speed as needed
        setY(newY);

        // Get the height of the screen
        int screenHeight = Gdx.graphics.getHeight();

        // Check if the bullet is out of bounds (reached the top edge of the screen)
        if (newY > screenHeight) {
            // If the bullet reaches the top edge, destroy it
            destroy();
        }
    }

    public void movement(float[] vector) {

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
        //decrementChangeRate();
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
        //decrementChangeRate();
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
        //decrementChangeRate();
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
