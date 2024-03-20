package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.player.PlayerControlManager;
import com.mygdx.game.util.iPlayerMovement;

class Bucket extends CollidableEntities implements iPlayerMovement {

    // Additional properties for Bucket class
    private final static String TEXTURE_PATH = "spaceship.png";

    private PlayerControlManager playerControlManager;

    // Default constructor
    protected Bucket() {
        //do nothing for now
    }

    // Constructor with ID
    protected Bucket(PlayerControlManager playerControlManager, int id, SpriteBatch batch) {
        super(id, batch);
        initializeBucket(playerControlManager);
    }

    // Parameterized constructor
    protected Bucket(PlayerControlManager playerControlManager, int id, int health, float x, float y, float scale, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture(BucketType.DEFAULT.getTexturePath())), width, height, speed, direction, batch);
        initializeBucket(playerControlManager);
    }

    // Initialize bucket properties
    private void initializeBucket(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
        this.setSprite(new Sprite(new Texture(BucketType.DEFAULT.getTexturePath())));
        this.setAlive(BucketType.DEFAULT.isAlive());
        this.setCollidable(BucketType.DEFAULT.isCollidable());
        this.setControl(BucketType.DEFAULT.getControl());
    }

    @Override
    protected void update() {
        movement();
        super.update();
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

    @Override
    // Movement method for player with movement direction lock
    public void movement() {
        if (getControl() == 'P') {
            float[] vector = playerControlManager.movement(this.getX(), this.getY(), this.getSpeed());
            switch (super.getDirection()) {         // 1 for up down, 2 for left right, 3 for all
                case 1:
                    setY(vector[1]);    // Set Y value
                    break;
                case 2:
                    setX(vector[0]);    //Set X value
                    break;
                case 3:
                    setX(vector[0]);    //Set X value
                    setY(vector[1]);    // Set Y value
                    break;
            }
        }
    }

    public void movement(float[] vector) {
        if (getControl() == 'P') {
            switch (super.getDirection()) {         // 1 for up down, 2 for left right, 3 for all
                case 1:
                    setY(vector[1]);    // Set Y value
                    break;
                case 2:
                    setX(vector[0]);    //Set X value
                    break;
                case 3:
                    setX(vector[0]);    //Set X value
                    setY(vector[1]);    // Set Y value
                    break;
            }
        }
    }
}

