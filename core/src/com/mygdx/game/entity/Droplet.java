package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Droplet extends Entity {

    // Additional properties for Droplet class
    private static final float DROPLET_SPEED = 100.0f;

    private SpriteBatch batch;

    // Default constructor
    public Droplet() {
        // do nothing don't touch
    }
    // Constructor with id parameter
    public Droplet(int id, SpriteBatch batch) {
        super(id, batch);
        this.setSprite(new Sprite(new Texture("droplet.png")));
        this.setAlive(true);
        this.setCollidable(true);
    }

    // Parameterized constructor
    public Droplet(int id, int health, float x, float y, float scale, int width, int height, int speed, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture("droplet.png")), width, height, speed, batch);
        this.setAlive(true);
        //Droplet droplet = new Droplet(id, health, boundingBox.x, boundingBox.y, scale, sprite, width, height, speed);
        this.setCollidable(true);
    }

    @Override
    public void update() {
        // Handle update logic for the droplet (AI-controlled movement)
        this.setY(this.getY() - Gdx.graphics.getDeltaTime() * DROPLET_SPEED);
        this.boundingBox.setPosition(getX(), getY());

        // Reset droplet if it goes below the screen
        if (this.getY() + this.getHeight() < 0) {
            this.setY(Gdx.graphics.getHeight());
            this.boundingBox.setPosition(getX(), getY());
        }
    }


    @Override
    public void render() {
        // Rendering logic for the droplet
    }

    @Override
    public boolean collidesWith(Entity other) {
        return getBoundingBox().overlaps(other.getBoundingBox());//if entity's bounding box overlaps another entities bounding box
    }

    @Override
    public void collideWith(Entity other) {
        // Collision logic for the droplet with another entity
        System.out.println("Collision Detected");

        //return false;
    }

    @Override
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

    @Override
    public void movement() {

    }
}




