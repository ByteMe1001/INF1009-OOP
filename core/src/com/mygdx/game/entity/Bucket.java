package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.player.PlayerControlManager;


public class Bucket extends Entity {

    // Additional properties for Bucket class
    private static final float BUCKET_SPEED = 200.0f; // not used??

    private final static String TEXTURE_PATH = "spaceship.png";

    private PlayerControlManager playerControlManager;

    // Default constructor
    public Bucket() {
        //do nothing for now
    }
    // Constructor with ID
    public Bucket(PlayerControlManager playerControlManager, int id, SpriteBatch batch) {
        super(id, batch);

        this.setSprite(new Sprite(new Texture(TEXTURE_PATH)));

        this.setAlive(true);
        this.setCollidable(true);
        this.setControl('P');
    }

    // Parameterized constructor
    public Bucket(PlayerControlManager playerControlManager,int id, int health, float x, float y, float scale, float width ,float height, int speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture(TEXTURE_PATH)), width, height, speed, direction, batch);
        setWidth(getSprite().getWidth());
        setHeight(getSprite().getHeight());
        this.playerControlManager = playerControlManager;
        this.setAlive(true);
        this.setCollidable(true);
        this.setControl('P');
    }


    @Override
    public void update() {
        movement();
        boundingBox.setPosition(getX(), getY());
        //System.out.println(boundingBox);
    }

    @Override
    public void render() {
        // Rendering logic for the bucket

    }

    /*
    @Override
    public boolean collidesWith(Entity other) {
        return getBoundingBox().overlaps(other.getBoundingBox());//if entity's bounding box overlaps another entities bounding box return true
    }
    */


/*
    //@Override
    public void collideWith(Entity other) {
        System.out.println("Collision Detected");
        //return false;
    }


    public boolean collideWith(Rectangle other) {
        // Collision logic for the bucket with another entity
        return false;
    }

     */

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

            setX(vector[0]);    //Set X value
            setY(vector[1]);    // Set Y value
        }
    }
}

