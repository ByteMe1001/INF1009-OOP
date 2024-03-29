package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
    private float x,y;               //Position
    private float scale;
    private boolean isAlive;
    private Sprite sprite;
    private SpriteBatch batch;

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


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~GETTER AND SETTER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void setSpriteSize() {
        this.sprite.setSize(getSpriteWidth() * getScale(), getSpriteHeight() * getScale());
    }

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

    public void update() {

    }

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
