package com.mygdx.game.gameEngine.util;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameLayer.movement.AIMovementStrategy;

import java.util.Random;

public interface iAiMovement {

    // Dont think it should be here, ask graham
    Random random = new Random();

//    public void setLeftRight();
//    public void setUpDown();
//    public void setAll();
//    public void setUp();
//    public void setDown();
//    public void setLeft();
//    public  void setRight();

    // Maybe clean up get and set x & y in abstract enttity?
    public float getX();
    public float getY();
    public void setX(float x);
    public void setY(float y);
    public float getSpeed();
    public float getSpriteWidth();
    public float getSpriteHeight();
//    public void movement(float[] vector);

    //~~~~~~~~~~~~~~~~~~NEEDED~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void movement();
    public int getChangeRate();
    public void setChangeRate(int i);
    public int getDefaultChangeRate();
    public AIMovementStrategy getMovementStrategy();

}
