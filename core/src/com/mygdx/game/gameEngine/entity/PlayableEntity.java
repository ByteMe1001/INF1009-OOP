package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.player.PlayerMovement;

public abstract class PlayableEntity extends CollidableEntities {

    private PlayerMovement playerMovement;

    // Constructors
    public PlayableEntity() {
        // No-arg constructor
    }

    public PlayableEntity(int id, SpriteBatch batch) {
        super(id, batch);
    }

    public PlayableEntity(int id, int health, float x, float y, float scale, Sprite sprite, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, sprite, width, height, speed, direction, batch);
    }

    public void movement() {
        // Get new coordinates and calculate
//        this.setX(vector[0]);
//        this.setY(vector[1]);
    }

    public void movement(float[] vector) {
        // Set new position
        //float[] vector = playerMovement.movement(this);
        this.setX(vector[0]);
        this.setY(vector[1]);
    }



    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~GETTER AND SETTER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public PlayerMovement getPlayerMovement() {
        return playerMovement;
    }

    public void setPlayerMovement(PlayerMovement playerMovement) {
        this.playerMovement = playerMovement;
    }
}
