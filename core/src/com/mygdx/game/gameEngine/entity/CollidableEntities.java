// CollidableEntities.java
package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.util.iCollision;

public abstract class CollidableEntities extends Entity implements iCollision {

    private int health;
    private float speed;
    protected Rectangle boundingBox;

    // Constructors
    public CollidableEntities() {
        // No-arg constructor
    }

    // TODO: TESTING CODE ONLY
    public CollidableEntities(SpriteBatch batch) {
        super(batch);
        this.health = 100;
        this.speed = 30;
    }

    // MAIN CONSTRUCTOR
    public CollidableEntities(int health, float x, float y, float scale, Sprite sprite,
                              float speed, SpriteBatch batch) {
        super(x, y, scale, sprite, batch);
        this.health = health;
        this.speed = speed;
        this.boundingBox =  new Rectangle(getX(), getY(), getSpriteWidth(), getSpriteHeight());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    // TODO: Shift bounding box logic to collision manage maybe?
    public void setBoundingBox() {
        // Update position of the bounding box, considering the offset
        this.boundingBox.setPosition(getX(), getY());
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    // Abstract Methods
    public abstract void takeDamage(int damage);

    public abstract void heal(int amount);

    public abstract void destroy();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~MAYBE DELETE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void draw() {
        super.draw();
    }

    // Maybe dunnid override
    @Override
    public void update() {
        setBoundingBox();
        checkHealth();
    }

    // To check health status for object deletion
    public void checkHealth() {
        if(getHealth() <= 0) {
            setAlive(false);
        }
    }

    public boolean collidesWith(iCollision other){
        return boundingBox.overlaps(other.getBoundingBox());
    }
}


    // Shift bounding box logic to collision manage maybe?
    // Method Overrides
//    public void push(float deltaX, float deltaY) {
//        super.push(deltaX, deltaY);
//    }
