package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bucket extends Entity {

    // Additional properties for Bucket class
    private static final int BUCKET_WIDTH = 64;
    private static final int BUCKET_HEIGHT = 64;
    private static final float BUCKET_SPEED = 200.0f;

    // Default constructor
    public Bucket(int id) {
        super(id);
        this.setWidth(BUCKET_WIDTH);
        this.setHeight(BUCKET_HEIGHT);
        this.setSprite(new Sprite(new Texture("bucket.png"))); // Assuming you have an image file named "bucket.png"
        this.setAlive(true);
    }

    // Parameterized constructor
    public Bucket(int id, int health, float x, float y, float scale, Sprite sprite) {
        super(id, health, x, y, scale, sprite);
        this.setWidth(BUCKET_WIDTH);
        this.setHeight(BUCKET_HEIGHT);
        this.setAlive(true);
    }

    @Override
    public void update() {
        // Handle player input for moving the bucket
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.setX(this.getX() - Gdx.graphics.getDeltaTime() * BUCKET_SPEED);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.setX(this.getX() + Gdx.graphics.getDeltaTime() * BUCKET_SPEED);
        }
    }

    @Override
    public void render() {
        // Rendering logic for the bucket
    }

    @Override
    public boolean collidesWith(Entity other) {
        // Collision logic for the bucket with another entity
        return false;
    }

    @Override
    public void takeDamage(int damage) {
        // Handle damage logic for the bucket
    }

    @Override
    public void heal(int amount) {
        // Handle healing logic for the bucket
    }

    @Override
    public void destroy() {
        // Handle destruction logic for the bucket
    }
}