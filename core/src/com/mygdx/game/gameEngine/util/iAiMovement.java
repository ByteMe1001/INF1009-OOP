package com.mygdx.game.gameEngine.util;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameLayer.movement.AIMovementStrategy;

import java.util.Random;

public interface iAiMovement {

    public float getX();
    public float getY();
    public void setX(float x);
    public void setY(float y);
    public float getSpeed();
    public float getSpriteWidth();
    public float getSpriteHeight();
    public void movement();
    public int getChangeRate();
    public void setChangeRate(int i);
    public int getDefaultChangeRate();
    public AIMovementStrategy getMovementStrategy();

}
