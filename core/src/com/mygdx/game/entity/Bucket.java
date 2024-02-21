package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.PlayerControlManager;


public class Bucket extends Entity {

    // Additional properties for Bucket class
    private static final float BUCKET_SPEED = 200.0f; // not used??
    private final static Character control = 'P';

    private PlayerControlManager playerControlManager;

    // Default constructor
    public Bucket() {
        //do nothing for now
    }
    // Constructor with ID
    public Bucket(int id, SpriteBatch batch) {
        super(id, batch);
        this.playerControlManager = new PlayerControlManager();

        this.setSprite(new Sprite(new Texture("bucket.png")));

        this.setSprite(new Sprite(new Texture("bucket.png"))); 

        this.setAlive(true);
        this.setCollidable(true);
        this.setControl('P');
    }

    // Parameterized constructor
    public Bucket(int id, int health, float x, float y, float scale, int width ,int height, int speed, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture("bucket.png")), width, height, speed, batch);
        this.playerControlManager = new PlayerControlManager();
        this.setAlive(true);
        this.setCollidable(true);
        this.setControl('P');
    }

    @Override
    public void update() {
        movement();
    }

    @Override
    public void render() {
        // Rendering logic for the bucket

    }

    @Override
    public boolean collidesWith(Entity other) {
        return getBoundingBox().overlaps(other.getBoundingBox());//if entity's bounding box overlaps another entities bounding box return true
    }


    //@Override
    public void collideWith(Entity other) {
        System.out.println("Collision Detected");
        //return false;
    }

    public boolean collideWith(Rectangle other) {
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


    public void movement() {

        if (getControl() == 'P') {
            float[] vector = playerControlManager.movement(this.getX(), this.getY(), this.getSpeed());
            if (vector[0] == 0f) {
                setX(vector[1]);        // Move horizontally
            }
            if (vector[0] == 1f) {
                setY(vector[1]);        // Move vectically
            }
        }
    }
}

