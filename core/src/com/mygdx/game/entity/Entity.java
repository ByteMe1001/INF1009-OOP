package com.mygdx.game.entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

//Not sure what else to import for now
public abstract class Entity {
    private int id;
    private int health;
    private float x,y;               //Position
    private float width, height;
    private float scale;
    private float speed;
    private int direction;          // 1 for up down, 2 for left right, 3 for all

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

      // prob wont use, how does the entity know what to create based on ID, unless you have a storage class, but that has to be inside entitymgr
    protected Entity(int id, SpriteBatch batch) {
      this.id = id;
      this.health = 100;        //Just sets default health can be adjusted if needed
      this.x = 0.0f;
      this.y = 0.0f;
      this.scale = 1.0f;        // *1.0 (no scaling)
      this.sprite = null;       // left it as null for now until we insert some placeholder default sprite
      this.width = 30;
      this.height = 30;
      //this.rect = new CollisionManager(x, y, width, height);

      boundingBox = new Rectangle(x, y, width, height);

      this.boundingBox = new Rectangle(x, y, width, height);
      this.batch = batch;

    }
   // Parameterized constructor
    protected Entity(int id, int health, float x, float y, float scale, Sprite sprite, float width, float height, float speed, int direction, SpriteBatch batch) {
        this.id = id;
        this.health = health;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.direction = direction;
        this.batch = batch;
        //this.collidable = collidable;
        //this.rect = new CollisionManager(x, y, width, height);
        boundingBox = new Rectangle(getX(), getY(), width, height);
    }
    //Setters and Getters methods 
    protected int getId() {
      return id;
    }

    protected void setId(int id) {
      this.id = id;
    }

    protected int getHealth() {
      return health;
    }

    protected void setHealth(int health) {
      this.health = health;
    }

    protected float getX() {
      return x;
    }

    protected void setX(float x) {
      this.x = x;
    }

    protected float getY() {
      return y;
    }

    protected void setY(float y) {
      this.y = y;
    }

    protected float getScale() {
      return scale;
    }

    protected void setScale(float scale) {
      this.scale = scale;
    }

    protected Sprite getSprite() {
      return sprite;
    }

    protected void setSprite(Sprite sprite) {
      this.sprite = sprite;
    }

    protected float getWidth() {
        return width;
    }

    protected void setWidth(float width) {
        this.width = width;
    }

    protected float getHeight() {
        return height;
    }

    protected void setHeight(float height) {
        this.height = height;
    }

    protected float getSpriteWidth() {
          return sprite.getWidth();
    }

    protected float getSpriteHeight() {
        return sprite.getHeight();
    }

    protected Character getControl() {
          return this.control;
    }

    protected void setControl(Character control) {
          this.control = control;
    }

    protected boolean isAlive() {
        return isAlive;
    }

    protected void setAlive(boolean alive) {
        isAlive = alive;
    }

    // for droplet use don't touch
    protected int getChangeRate() {
       return 1;
    }

    protected int getDefaultChangeRate() {
      return 1;
    }

    protected void setChangeRate(int changeRate) {

    }

    protected int getDirection() {
          return this.direction;
    }

    protected void setDirection(int direction) {
          this.direction = direction;
    }

    protected String getCurrentDirection() {
      return this.currentDirection;
    }

    protected void setCurrentDirection(String currentDirection) {
      this.currentDirection = currentDirection;
    }
    //Methods
    protected void update() {
        boundingBox.setPosition(getX(), getY());
    }


    //protected abstract boolean collidesWith(Entity other);       //Can fine-tune what each entity class is able to collide with using this abstract method

    protected boolean isCollidable(){
        return isCollidable;
    };

    protected void setCollidable(boolean collidable){
        isCollidable = collidable;
    };

    //protected abstract void collideWith(Entity other);

    protected float getSpeed() {
          return speed;
      }

    protected void setSpeed(int speed) {
          this.speed = speed;
      }

     // protected abstract void collideWith(Entity other);

    protected abstract void takeDamage(int damage);              //Not sure if we need these 2 damage/heal

    protected abstract void heal(int amount);

    protected abstract void destroy();

    protected abstract void movement();

    /*
    protected CollisionManager getRect(){ //get entity position?
      return rect;
    }         //might not be using this
*/
    protected void push(float deltaX, float deltaY) {  //Simple push method maybe can implement for collisions,pretty much just knockback
      this.x += deltaX;
      this.y += deltaY;
    }

    protected Rectangle getBoundingBox(){
        return boundingBox;
    }

    //added dispose method
    protected void dispose() {
        if (sprite != null && sprite.getTexture() != null) {
            sprite.getTexture().dispose();
        }
    }

    // Draw function for manager to use
    protected void draw() {
        batch.draw(sprite, this.x, this.y, sprite.getWidth(), sprite.getHeight());
    }
}
  
