package com.mygdx.game.gameLayer.entity;

public enum EntityType {
    CHARACTER(1, 100, 100f, 100f, 1f, 64f,
            64f, 300f,3, true, 'P',
            true, "spaceship.png"),
    BOSS(2, 100, 500f, 500f, 1f, 64f,
            64f, 300f, 3, 0,
            true, 'A', true, "asteroid.png");

    private final int id;
    private final int health;

    private final float x;
    private final float y;
    private final float scale;
    private final float width;
    private final float height;
    private final float speed;
    private final int direction;
    private final int changeRate;
    private final boolean isAlive;
    private final Character control;
    private final boolean isCollidable;
    private final String texturePath;

    EntityType(int id, int health, float x, float y, float scale, float width, float height,
               float speed, int direction, int changeRate, boolean isAlive, Character control,
               boolean isCollidable, String texturePath) {
        this.id = id;
        this.health = health;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.direction = direction;
        this.changeRate = changeRate;
        this.isAlive = isAlive;
        this.control = control;
        this.isCollidable = isCollidable;
        this.texturePath = texturePath;
    }

    // Constructor without changeRate parameter (defaults to 0)
    EntityType(int id, int health, float x, float y, float scale, float width, float height,
               float speed, int direction, boolean isAlive, Character control, boolean isCollidable,
               String texturePath) {
        this(id, health, x, y, scale, width, height, speed, direction, 0, isAlive, control, isCollidable,
                texturePath);
    }

}
