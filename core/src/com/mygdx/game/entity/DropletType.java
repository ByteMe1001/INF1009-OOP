package com.mygdx.game.entity;

// Enum class to store specific entity values
// EDIT values here to change entity blueprint
public enum DropletType {
    DEFAULT(2, 100, 500f, 500f, 1f, 64f, 64f, 300f,3, 30, true, 'A', true, "asteroid.png");
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

    DropletType(int id, int health, float x, float y, float scale, float width, float height, float speed, int direction, int changeRate, boolean isAlive, Character control, boolean isCollidable, String texturePath) {
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

    public int getChangeRate() {
        return changeRate;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Character getControl() {
        return control;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public String getTexturePath() {
        return texturePath;
    }
}