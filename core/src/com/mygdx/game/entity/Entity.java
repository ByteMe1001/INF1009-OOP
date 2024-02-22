package com.mygdx.game.entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CollisionManager;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.iCollision;

//Not sure what else to import for now
public abstract class Entity
  {
    private int id;
    private int health;
    private float x,y;               //Position
    private int width, height;
    private float scale;
    private int speed;
    private int direction;          // 1 for up down, 2 for left right, 3 for all
    private Sprite sprite;
    private SpriteBatch batch;
    private boolean isAlive;
    private CollisionManager rect;

    private boolean isCollidable;

    protected Rectangle boundingBox;
    private Character control; // CAPITAL LETTER Char to designate who controls it. A for AI, P for Player, N for nil


    // Default constructor
      public Entity() {
          // do nothing
      }

      // prob wont use, how does the entity know what to create based on ID, unless you have a storage class, but that has to be inside entitymgr
    public Entity(int id, SpriteBatch batch) {
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
    public Entity(int id, int health, float x, float y, float scale, Sprite sprite, int width, int height, int speed, int direction, SpriteBatch batch) {
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    // for droplet use don't touch
    public int getChangeRate() {
       return 1;
    }

    public void setChangeRate(int changeRate) {

    }

    public int getDirection() {
          return this.direction;
    }

    public void setDirection(int direction) {
          this.direction = direction;
    }
    //Methods
    public abstract void update();

    public abstract void render();

    public abstract boolean collidesWith(Entity other);       //Can fine-tune what each entity class is able to collide with using this abstract method

    public boolean isCollidable(){
        return isCollidable;
    };

    public void setCollidable(boolean collidable){
        isCollidable = collidable;
    };

    //public abstract void collideWith(Entity other);

    public int getSpeed() {
          return speed;
      }

    public void setSpeed(int speed) {
          this.speed = speed;
      }

     // public abstract void collideWith(Entity other);

    public abstract void takeDamage(int damage);              //Not sure if we need these 2 damage/heal

    public abstract void heal(int amount);

    public abstract void destroy();

    public abstract void movement();

    public CollisionManager getRect(){ //get entity position?
      return rect;
    }         //might not be using this

    public void push(float deltaX, float deltaY) {  //Simple push method maybe can implement for collisions,pretty much just knockback
      this.x += deltaX;
      this.y += deltaY;
    }

    public Rectangle getBoundingBox(){
        return boundingBox;
    };
    //added dispose method
    public void dispose() {
        if (sprite != null && sprite.getTexture() != null) {
           sprite.getTexture().dispose();
       }
    }

    // Draw function for manager to use
    public void draw() {
        batch.draw(sprite, this.x, this.y, sprite.getWidth(), sprite.getHeight());
    }
    public String testString() {
      return "Entity{" +
             "id=" + id +
             ", health=" + health +
             ", x=" + x +
             ", y=" + y +
             ", scale=" + scale +
             ", sprite=" + sprite +
             "}";
    }


  }
  
