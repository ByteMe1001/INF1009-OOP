// CollidableEntities.java
package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.util.iCollision;

public abstract class CollidableEntities extends Entity implements iCollision {

    // Constructors
    public CollidableEntities() {
        // No-arg constructor
    }

    public CollidableEntities(int id, SpriteBatch batch) {
        super(id, batch);
        setCollidable(true); // Marking as collidable
    }

    public CollidableEntities(int id, int health, float x, float y, float scale, Sprite sprite, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, sprite, width, height, speed, direction, batch);
        setCollidable(true); // Marking as collidable
    }

    // Abstract Methods
    public abstract void takeDamage(int damage);

    public abstract void heal(int amount);

    public abstract void destroy();

    // Override movement method with public access
    @Override
    public abstract void movement();

    // Method Overrides
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void push(float deltaX, float deltaY) {
        super.push(deltaX, deltaY);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public Rectangle getBoundingBox(){
        return boundingBox;
    }

    @Override
    public boolean collidesWith(Entity other){
        return boundingBox.overlaps(other.getBoundingBox());
    }
}
