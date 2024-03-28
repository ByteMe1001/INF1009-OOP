package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {

//    private int id;       probably dunnid
    private float x,y;               //Position
    private float scale;
    private boolean isAlive;
    private Sprite sprite;
    private SpriteBatch batch;
//    private int id;
//    private int health;
//    private float width, height;
//    private float speed;
//    private int movementSetID;          // 1 for up down, 2 for left right, 3 for all
//
//    private String currentDirection = "";
//    private boolean isAlive;
    //private CollisionManager rect;

//    private boolean isCollidable;
//
//    protected Rectangle boundingBox;
//    private Character control; // CAPITAL LETTER Char to designate who controls it. A for AI, P for Player, N for nil
//

    // Default constructor
      public Entity() {
          // do nothing
      }

    public Entity(SpriteBatch batch) {
      this.x = 0.0f;
      this.y = 0.0f;
      this.scale = 1.0f;        // *1.0 (no scaling)
      this.isAlive = true;
      this.sprite = null;
      this.batch = batch;
    }

   // Parameterized constructor
    public Entity(float x, float y, float scale, Sprite sprite, SpriteBatch batch) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.isAlive = true;
        this.sprite = sprite;
        this.batch = batch;
        setSpriteSize();
    }

    public void setSpriteSize() {
        this.sprite.setSize(getSpriteWidth() * getScale(), getSpriteHeight() * getScale());
    }

    //Setters and Getters methods

    public float getX() {
      return x;
    }

    public void setX(float x) {
      this.x = x;
    }

    public float getY() {
      return y;
    }

    public void setY(float y) {
      this.y = y;
    }

    public float getScale() {
      return scale;
    }

    public void setScale(float scale) {
      this.scale = scale;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Sprite getSprite() {
      return sprite;
    }

    public void setSprite(Sprite sprite) {
      this.sprite = sprite;
    }

    public float getSpriteWidth() {
          return sprite.getWidth();
    }

    public float getSpriteHeight() {
        return sprite.getHeight();
    }


    // Shift bounding box logic to collision manage maybe?


    //Methods
    // Move to colliadable???
    public abstract void takeDamage(int damage);

    public abstract void heal(int amount);

    public abstract void destroy();
    public void update() {

    }
    // END

    // Dispose method
    public void dispose() {
        if (sprite != null && sprite.getTexture() != null) {
            sprite.getTexture().dispose();
        }
    }

    // Draw function for manager to use
    public void draw() {
        batch.draw(sprite, this.x, this.y, sprite.getWidth(), sprite.getHeight());
    }


}
