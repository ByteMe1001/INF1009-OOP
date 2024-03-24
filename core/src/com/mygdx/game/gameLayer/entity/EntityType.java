package com.mygdx.game.gameLayer.entity;

import com.mygdx.game.gameEngine.player.PlayerMovement;
import com.mygdx.game.gameLayer.movement.Player1MovementStrategy;

public enum EntityType {
    BOY(100, 100f, 100f, 1f,
            300f, new Player1MovementStrategy(),
            "spaceship.png"),
    BOSS(100, 500f, 500f, 1f,
            200f, 30, "asteroid.png"),

    // Leaving playermovement as null for now
    BULLET(10, 0, 0, 1f,
            200f, 0, "bullet.png");

    public final int health;

    public final float x;
    public final float y;
    public final float scale;
    public final float speed;
    public final int defaultChangeRate;
    public final String texturePath;
    public final PlayerMovement playerMovement;

    // FOR ALL ARGUMENTS, AS TEMPLATE
    // Constructor with PlayerMovement parameter & change rate
    EntityType(int health, float x, float y, float scale,
               float speed, int defaultChangeRate, PlayerMovement playerMovement,
               String texturePath) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.speed = speed;
        this.defaultChangeRate = defaultChangeRate;
        this.playerMovement = playerMovement;
        this.texturePath = texturePath;
    }

    // FOR PLAYABLE ENTITY
    // Constructor without changeRate parameter (defaults to 0)
    EntityType(int health, float x, float y, float scale,
               float speed, PlayerMovement playerMovement,
               String texturePath) {
        this(health, x, y, scale, speed, 0, playerMovement, texturePath);
    }

    // FOR AI ENTITY
    // Constructor without PlayerMovement parameter (assumes default PlayerMovement)
    EntityType(int health, float x, float y, float scale,
               float speed, int defaultChangeRate, String texturePath) {
        this(health, x, y, scale, speed, defaultChangeRate, /* default PlayerMovement */ null, texturePath);
    }

}
