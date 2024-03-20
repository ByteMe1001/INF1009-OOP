package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class NonCollidableEntities extends Entity {

    // Constructors
    protected NonCollidableEntities() {
        // No-arg constructor
    }

    public NonCollidableEntities(int id, SpriteBatch batch) {
        super(id, batch);
        setCollidable(false); // Marking as non-collidable
    }

    protected NonCollidableEntities(int id, int health, float x, float y, float scale, Sprite sprite, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, sprite, width, height, speed, direction, batch);
        setCollidable(false); // Marking as non-collidable
    }

    // Abstract Methods
    protected abstract void takeDamage(int damage);

    protected abstract void heal(int amount);

    protected abstract void destroy();

    public abstract void movement();

    // Method Overrides
    @Override
    protected void update() {
        super.update();
    }

    @Override
    protected void dispose() {
        super.dispose();
    }

    @Override
    protected void draw() {
        super.draw();
    }
}
