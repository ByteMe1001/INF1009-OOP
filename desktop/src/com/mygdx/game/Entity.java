package com.mygdx.game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//Not sure what else to import 
public abstract class Entity
  {
    private int id;                  //Id
    private float x,y;               //Position
    private float xScale,yScale;     //Scale
    //private control string;          //Control input
    
    
    //Constructors
    public Entity()
    {
    }
      public Entity(float x, float y)
    {
      this.x = x; 
      this.y = y;
    }
      public Entity(float x, float y ,float xScale ,float yScale)
    {
      this.x = x; 
      this.y = y; 
      this.xScale = xScale;
      this.yScale = yScale;
    }
    //Getters and Setters
      public float getX()
    {
        return x;
    }
      public float getY()
    {
        return y;
    }
      public float getxScale()
    {
        return xScale;
    }
      public float getyScale()
    {
        return yScale;
    }
      public void setX(float x) 
    {
        this.x = x;
    }
      public void setY(float y)
    {
        this.y = y;
    }
      public void setXScale(float xScale)
    {
        this.xScale = xScale;
    }
      public void setYScale(float yScale)
    {
        this.yScale = yScale;
    }
 //methods
      public abstract void render();
      public abstract void update();
      public abstract void movement();
  }
