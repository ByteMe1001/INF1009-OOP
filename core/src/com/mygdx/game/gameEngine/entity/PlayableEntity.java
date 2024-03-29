package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.player.PlayerMovement;
import com.mygdx.game.gameEngine.util.iPlayerMovement;

public abstract class PlayableEntity extends CollidableEntities implements iPlayerMovement {

    private PlayerMovement playerMovement; // The movement strategy for the player
    private int maxHealth; // The maximum health of the player

    // Constructors
    public PlayableEntity() {
        // No-arg constructor
    }


    // Constructor with variables
    public PlayableEntity(int health, int maxHealth, float x, float y, float scale,
                          Sprite sprite, float speed, PlayerMovement playerMovement, SpriteBatch batch) {
        super(health, x, y, scale, sprite, speed, batch);
        this.maxHealth = maxHealth;
        this.playerMovement = playerMovement;
    }

    // Moves the entity to the given coordinates.
    public void movement(float[] vector) {
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

    public int getMaxHealth() {
        return maxHealth;
    }

}
