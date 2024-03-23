package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameEngine.entity.PlayableEntity;
import com.mygdx.game.gameEngine.player.PlayerControlManager;
import com.mygdx.game.gameEngine.player.PlayerMovement;
import com.mygdx.game.gameEngine.util.iPlayerMovement;
import com.mygdx.game.gameLayer.movement.Player1MovementStrategy;

public class Boy extends PlayableEntity {

    // Additional properties for Boy class
    private final static String TEXTURE_PATH = "spaceship.png";

    private PlayerMovement playerMovementStrategy;

    // Default constructor
    public Boy() {
        //do nothing for now
    }

    // Constructor with ID
    public Boy(SpriteBatch batch) {
        super(batch);
    }

    // Constructor with ID
    public Boy(int health, float x, float y, float scale, Sprite sprite,
              float speed, PlayerMovement playerMovement, SpriteBatch batch) {
        super(health, x, y, scale, sprite ,speed, playerMovement, batch);
        // TODO: upgrade to dynamic assignment of strat?
        this.playerMovementStrategy = new Player1MovementStrategy();
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~GETTER AND SETTER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public PlayerMovement getPlayerMovementStrategy() {
        return playerMovementStrategy;
    }

    public void setPlayerMovementStrategy(PlayerMovement playerMovementStrategy) {
        this.playerMovementStrategy = playerMovementStrategy;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~GAME LOGIC~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public void update() {
        //movement();
        super.update();
    }

    @Override
    public void takeDamage(int damage) {
        // Handle damage logic for the Boy
    }

    @Override
    public void heal(int amount) {
        // Handle healing logic for the Boy
    }

    @Override
    public void destroy() {
        // Handle destruction logic for the Boy
    }

    @Override
    // Movement method for player with movement direction lock
    public void movement() {

    }

    // TO USE
    public void movement(String direction) {
        float [] vector = playerMovementStrategy.calculateMovement(this, direction);
        super.movement(vector);
    }
}


//    public void movement(float[] vector) {
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


    // Initialize Boy properties
//    private void initializeBucket(PlayerControlManager playerControlManager) {
//        this.playerControlManager = playerControlManager;
//        this.setSprite(new Sprite(new Texture(BoyType.DEFAULT.getTexturePath())));
//        this.setAlive(BoyType.DEFAULT.isAlive());
//        this.setCollidable(BoyType.DEFAULT.isCollidable());
//        this.setControl(BoyType.DEFAULT.getControl());
//    }
