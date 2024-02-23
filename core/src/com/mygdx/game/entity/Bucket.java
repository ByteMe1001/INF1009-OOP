package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.player.PlayerControlManager;


public class Bucket extends Entity {

    // Additional properties for Bucket class

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
    protected void update() {
        movement();
        boundingBox.setPosition(getX(), getY());
        //System.out.println(boundingBox);
    }

    @Override
    protected void render() {
        // Rendering logic for the bucket

    }

    @Override
    protected void takeDamage(int damage) {
        // Handle damage logic for the bucket
    }

    @Override
    protected void heal(int amount) {
        // Handle healing logic for the bucket
    }

    @Override
    protected void destroy() {
        // Handle destruction logic for the bucket
    }

    protected void movement() {

        if (getControl() == 'P') {
            float[] vector = playerControlManager.movement(this.getX(), this.getY(), this.getSpeed());

            setX(vector[0]);    //Set X value
            setY(vector[1]);    // Set Y value
        }
    }
}

