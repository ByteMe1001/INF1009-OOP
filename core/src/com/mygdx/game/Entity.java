package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.CollisionManager;
import com.badlogic.gdx.math.Rectangle;

//Not sure what else to import for now
public abstract class Entity
  {
    private int id;
    private int health;
    private float x,y;               //Position
    private int width, height;
    private float scale;
    private Sprite sprite;
    private boolean isAlive;
    private CollisionManager rect;

    Rectangle boundingBox;

    // Default constructor
    public Entity(int id) {
      this.id = id;
      this.health = 100;        //Just sets default health can be adjusted if needed
      this.x = 0.0f;
      this.y = 0.0f;
      this.scale = 1.0f;        // *1.0 (no scaling)
      this.sprite = null;       // left it as null for now until we insert some placeholder default sprite
      this.width = 30;
      this.height = 30;
      this.rect = new CollisionManager(x, y, width, height);
      this.boundingBox = new Rectangle(x, y, width, height);
    }
   // Parameterized constructor
    public Entity(int id, int health, float x, float y, float scale, Sprite sprite, int width, int height) {
        this.id = id;
        this.health = health;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.rect = new CollisionManager(x, y, width, height);
        this.boundingBox = new Rectangle(x, y, width, height);
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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    //Methods
    public abstract void update();

    public abstract void render();

    public abstract boolean collideWith(Entity other);       //Can fine-tune what each entity class is able to collide with using this abstract method

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




    public boolean intersects(Rectangle other){
        return boundingBox.overlaps(other);
    }

// Duplicate method
//    public void setScale(float scale) {
//      this.scale = scale;
//    }                                                         //Mario mushroom

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
  
