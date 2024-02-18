package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.Sprite;

//Not sure what else to import for now
public abstract class Entity
  {
    protected int id;                 
    protected int health;              
    protected float x,y;               //Position
    protected float scale;     
    protected Sprite sprite;          


    // Default constructor
    public Entity(int id) {
      this.id = id;
      this.health = 100;        //Just sets default health can be adjusted if needed
      this.x = 0.0f;
      this.y = 0.0f;
      this.scale = 1.0f;        // *1.0 (no scaling)
      this.sprite = null;       // left it as null for now until we insert some placeholder default sprite
    }
    //parameterized constructor
    public Entity(int id, int health ,float x , float y, float scale, Sprite sprite) {
      this.id = id; 
      this.health = health;
      this.x = x;
      this.y = y;
      this.scale = scale;
      this.sprite = sprite;
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
    //Methods 
    public abstract void update();

    public abstract void render();
    
    public abstract boolean collidesWith(Entity other);       //Can fine-tune what each entity class is able to collide with using this abstract method

    public abstract void takeDamage(int damage);              //Not sure if we need these 2 damage/heal

    public abstract void heal(int amount);             
    
    public abstract void destroy();

    public void push(float deltaX, float deltaY) {
      this.x += deltaX;
      this.y += deltaY;
    }                                                         //Simple push method maybe can implement for collisions,pretty much just knockback

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
  
