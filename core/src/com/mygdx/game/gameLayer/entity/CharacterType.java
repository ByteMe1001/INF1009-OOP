package com.mygdx.game.gameLayer.entity;

// Enum class to store specific entity values
// EDIT values here to change entity blueprint
public enum CharacterType {
    DEFAULT(1, 100, 100f, 100f, 1f, 64f, 64f, 300f,3, true, 'P', true, "spaceship.png");
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
    private final Character control;
    private final boolean isCollidable;
    private final String texturePath;

    CharacterType(int id, int health, float x, float y, float scale, float width, float height, float speed, int direction, boolean isAlive, Character control, boolean isCollidable, String texturePath) {
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