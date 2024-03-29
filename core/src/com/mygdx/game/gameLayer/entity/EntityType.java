package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.player.PlayerMovement;
import com.mygdx.game.gameLayer.movement.Player1MovementStrategy;

public enum EntityType {

    /** BOY = 0
     * ENEMY = 1
     * BOSS = 2
     * BULLET = 3
     * ENEMYBULLET = 4
     * HEALTHPACK = 5
     */

    BOY(100, 100, 100f, 100f, 1f,
            300f, new Player1MovementStrategy(),
            "Boy.png"),
    ENEMY(50, 500f, 500f, 1f,
            200f, 30, "gameBoss.png"),
    BOSS(100, 500f, (Gdx.graphics.getHeight() / 1.5f), 3f,
            300f, 40, "Boss.png"),
    BULLET(10, 0, 0, 1f,
            400f, 0, "bullet.png"),
    ENEMYBULLET(10, 0, 0, 1f,
            200f, 0, "81_pizza.png"),
    HEALTHPACK(10, 500f, 500f, 1f,
                        100f, 50, "Broccoli.png");

    public final int health;
    public final int maxHealth;
    public final float x;
    public final float y;
    public final float scale;
    public final float speed;
    public final int defaultChangeRate;
    public final String texturePath;
    public final PlayerMovement playerMovement;

    // FOR ALL ARGUMENTS, AS TEMPLATE
    // Constructor with PlayerMovement parameter & change rate
    EntityType(int health, int maxHealth, float x, float y, float scale,
               float speed, int defaultChangeRate, PlayerMovement playerMovement,
               String texturePath) {
        this.health = health;
        this.maxHealth = maxHealth;
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
    EntityType(int health, int maxHealth, float x, float y, float scale,
               float speed, PlayerMovement playerMovement,
               String texturePath) {
        this(health, maxHealth, x, y, scale, speed, 0, playerMovement, texturePath);
    }

    // FOR AI ENTITY
    // Constructor without PlayerMovement parameter (assumes default PlayerMovement)
    EntityType(int health, float x, float y, float scale,
               float speed, int defaultChangeRate, String texturePath) {
        this(health, 0, x, y, scale, speed, defaultChangeRate, /* default PlayerMovement */ null, texturePath);
    }

}
