package com.mygdx.game.GameEngine.Entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
    private int id;
    private int health;
    private float x,y;               //Position
    private float width, height;
    private float scale;
    private float speed;
    private int movementSetID;          // 1 for up down, 2 for left right, 3 for all

    private String currentDirection = "";
    private Sprite sprite;
    private SpriteBatch batch;
    private boolean isAlive;
    //private CollisionManager rect;

    private boolean isCollidable;

    protected Rectangle boundingBox;
    private Character control; // CAPITAL LETTER Char to designate who controls it. A for AI, P for Player, N for nil


    // Default constructor
      public Entity() {
          // do nothing
      }

    protected Entity(int id, SpriteBatch batch) {
      this.id = id;
      this.health = 100;        //Just sets default health can be adjusted if needed
      this.x = 0.0f;
      this.y = 0.0f;
      this.scale = 1.0f;        // *1.0 (no scaling)
      this.sprite = null;       // left it as null for now until we insert some placeholder default sprite
      this.width = 30;
      this.height = 30;
      this.boundingBox = new Rectangle(x, y, width, height);
      this.batch = batch;
    }

   // Parameterized constructor
    protected Entity(int id, int health, float x, float y, float scale, Sprite sprite, float width, float height, float speed, int movementSetID, SpriteBatch batch) {
        this.id = id;
        this.health = health;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.movementSetID = movementSetID;
        this.batch = batch;
        this.boundingBox = new Rectangle(getX(), getY(), width, height);
    }

    //Setters and Getters methods 
    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getHealth() {
      return health;
    }

    public void setHealth(int health) {
      this.health = health;
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

    public Sprite getSprite() {
      return sprite;
    }

    public void setSprite(Sprite sprite) {
      this.sprite = sprite;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getSpriteWidth() {
          return sprite.getWidth();
    }

    public float getSpriteHeight() {
        return sprite.getHeight();
    }

    public Character getControl() {
          return this.control;
    }

    public void setControl(Character control) {
          this.control = control;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }



    public void setChangeRate(int changeRate) {

    }

    public int getMovementSetID() {
          return this.movementSetID;
    }

    public void setMovementSetID(int movementSetID) {
          this.movementSetID = movementSetID;
    }

    public String getCurrentDirection() {
      return this.currentDirection;
    }

    public void setCurrentDirection(String currentDirection) {
      this.currentDirection = currentDirection;
    }
    //Methods

    // Shift bounding box logic to collision manage maybe?
    public void update() {
        boundingBox.setPosition(getX(), getY());
    }



    public boolean isCollidable(){
        return isCollidable;
    };

    public void setCollidable(boolean collidable){
        isCollidable = collidable;
    };

    public float getSpeed() {
          return speed;
      }

    public void setSpeed(int speed) {
          this.speed = speed;
      }
    public abstract void takeDamage(int damage);              // for game use

    public abstract void heal(int amount);

    public abstract void destroy();

    public abstract void movement();

    public void push(float deltaX, float deltaY) {  //Simple push method maybe can implement for collisions,pretty much just knockback
      this.x += deltaX;
      this.y += deltaY;
    }

    //dispose method
    public void dispose() {
        if (sprite != null && sprite.getTexture() != null) {
            sprite.getTexture().dispose();
        }
    }

    // Draw function for manager to use
    public void draw() {
        batch.draw(sprite, this.x, this.y, sprite.getWidth(), sprite.getHeight());
    }

    public abstract Rectangle getBoundingBox();


}
