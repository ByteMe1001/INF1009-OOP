package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class NonCollidableEntities extends Entity {

    // Constructors
    public NonCollidableEntities() {
        // No-arg constructor
    }

    public NonCollidableEntities(int id, SpriteBatch batch) {
        super(id, batch);
        setCollidable(false); // Marking as non-collidable
    }

    public NonCollidableEntities(int id, int health, float x, float y, float scale, Sprite sprite, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, sprite, width, height, speed, direction, batch);
        setCollidable(false); // Marking as non-collidable
    }

    // Abstract Methods
    public abstract void takeDamage(int damage);

    public abstract void heal(int amount);

    public abstract void destroy();

    public abstract void movement();

    // Method Overrides
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void draw() {
        super.draw();
    }
}
