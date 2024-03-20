// CollidableEntities.java
package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.iCollision;

public abstract class CollidableEntities extends Entity implements iCollision {

    // Constructors
    protected CollidableEntities() {
        // No-arg constructor
    }

    public CollidableEntities(int id, SpriteBatch batch) {
        super(id, batch);
        setCollidable(true); // Marking as collidable
    }

    protected CollidableEntities(int id, int health, float x, float y, float scale, Sprite sprite, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, sprite, width, height, speed, direction, batch);
        setCollidable(true); // Marking as collidable
    }

    // Abstract Methods
    protected abstract void takeDamage(int damage);

    protected abstract void heal(int amount);

    protected abstract void destroy();

    // Override movement method with public access
    @Override
    public abstract void movement();

    // Method Overrides
    @Override
    protected void update() {
        super.update();
    }

    @Override
    protected void push(float deltaX, float deltaY) {
        super.push(deltaX, deltaY);
    }

    @Override
    protected void dispose() {
        super.dispose();
    }

    @Override
    protected void draw() {
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
