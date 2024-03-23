package com.mygdx.game.gameLayer.entity;

import com.mygdx.game.gameEngine.player.PlayerMovement;
import com.mygdx.game.gameLayer.movement.Player1MovementStrategy;

public enum EntityType {
    BOY(100, 100f, 100f, 1f,
            300f, new Player1MovementStrategy(),
            "spaceship.png"),
    BOSS(100, 500f, 500f, 1f,
            300f, 30, "asteroid.png");

    public final int health;

    public final float x;
    public final float y;
    public final float scale;
    public final float speed;
    public final int changeRate;
    public final String texturePath;
    public final PlayerMovement playerMovement;

    // Constructor with PlayerMovement parameter
    EntityType(int health, float x, float y, float scale,
               float speed, int changeRate, PlayerMovement playerMovement,
               String texturePath) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.speed = speed;
        this.changeRate = changeRate;
        this.playerMovement = playerMovement;
        this.texturePath = texturePath;
    }

    // Constructor without changeRate parameter (defaults to 0)
    EntityType(int health, float x, float y, float scale,
               float speed, PlayerMovement playerMovement,
               String texturePath) {
        this(health, x, y, scale, speed, 0, playerMovement, texturePath);
    }

    // Constructor without PlayerMovement parameter (assumes default PlayerMovement)
    EntityType(int health, float x, float y, float scale,
               float speed, int changeRate, String texturePath) {
        this(health, x, y, scale, speed, changeRate, /* default PlayerMovement */ null, texturePath);
    }

}
