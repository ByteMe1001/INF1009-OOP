package com.mygdx.game.gameLayer.entity;

public enum BulletType {
    DEFAULT(1, 100, 100f, 100f, 1f, 10f, 10f, 500f, 3, true, 'P', true, "bullet1.png", 30);

    private final int id;
    private final int health;
    private final float x;
    private final float y;
    private final float scale;
    private final float width;
    private final float height;
    private final float speed;
    private final int direction;
    private final boolean isAlive;
    private final char control;
    private final boolean isCollidable;
    private final String texturePath;
    private final int changeRate;

    BulletType(int id, int health, float x, float y, float scale, float width, float height, float speed, int direction, boolean isAlive, char control, boolean isCollidable, String texturePath, int changeRate) {
        this.id = id;
        this.health = health;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.direction = direction;
        this.isAlive = isAlive;
        this.control = control;
        this.isCollidable = isCollidable;
        this.texturePath = texturePath;
        this.changeRate = changeRate;
    }

    // Getters for all properties
    public int getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getScale() {
        return scale;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public char getControl() {
        return control;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public int getChangeRate() {
        return changeRate;
    }
}
