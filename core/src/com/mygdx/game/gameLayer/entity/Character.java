package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameEngine.player.PlayerControlManager;
import com.mygdx.game.gameEngine.util.iPlayerMovement;

public class Character extends CollidableEntities implements iPlayerMovement {

    // Additional properties for Bucket class
    private final static String TEXTURE_PATH = "spaceship.png";

    private PlayerControlManager playerControlManager;

    // Default constructor
    public Character() {
        //do nothing for now
    }

    // Constructor with ID
    public Character(int id, int health, float x, float y, float scale, Sprite sprite,
                     float width, float height, float speed, int direction,
                     PlayerControlManager playerControlManager, boolean isAlive,
                     boolean isCollidable , SpriteBatch batch) {
        super(id, health, x, y, scale, sprite, width, height, speed, direction, batch);
        this.playerControlManager = playerControlManager;
        this.setAlive(isAlive);
        this.setCollidable(isCollidable);
    }

    // Another constructor if needed
    public Character(int id, PlayerControlManager playerControlManager, SpriteBatch batch) {
        super(id, 100, 0, 0, 1f, new Sprite(new Texture(TEXTURE_PATH)), 64f, 64f, 300f, 3, batch);
        initializeBucket(playerControlManager);
    }

    // Additional constructor if needed
    public Character(int id, PlayerControlManager playerControlManager, SpriteBatch batch, float x, float y) {
        super(id, 100, x, y, 1f, new Sprite(new Texture(TEXTURE_PATH)), 64f, 64f, 300f, 3, batch);
        initializeBucket(playerControlManager);
    }

    // Initialize bucket properties
    private void initializeBucket(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
        this.setSprite(new Sprite(new Texture(CharacterType.DEFAULT.getTexturePath())));
        this.setAlive(CharacterType.DEFAULT.isAlive());
        this.setCollidable(CharacterType.DEFAULT.isCollidable());
        this.setControl(CharacterType.DEFAULT.getControl());
    }

    @Override
    public void update() {
        //movement();
        super.update();
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

    @Override
    // Movement method for player with movement direction lock
    public void movement() {
//        if (getControl() == 'P') {
//            float[] vector = playerControlManager.movement(this.getX(), this.getY(), this.getSpeed());
//            switch (super.getMovementSetID()) {         // 1 for up down, 2 for left right, 3 for all
//                case 1:
//                    setY(vector[1]);    // Set Y value
//                    break;
//                case 2:
//                    setX(vector[0]);    //Set X value
//                    break;
//                case 3:
//                    setX(vector[0]);    //Set X value
//                    setY(vector[1]);    // Set Y value
//                    break;
//            }
//        }
    }

    public void movement(float[] vector) {
        if (getControl() == 'P') {
            switch (super.getMovementSetID()) {         // 1 for up down, 2 for left right, 3 for all
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

