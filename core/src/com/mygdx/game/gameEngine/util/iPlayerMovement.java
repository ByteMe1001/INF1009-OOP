package com.mygdx.game.gameEngine.util;

import com.mygdx.game.gameEngine.player.PlayerMovement;

public interface iPlayerMovement {
    public void movement(float[] vector);       // Deprecated!!!!
    public void movement(String direction);
    public PlayerMovement getPlayerMovementStrategy();
    public float getX();
    public float getY();
    public float getSpeed();
    public float getSpriteWidth();
    public float getSpriteHeight();
    public void shoot();

}
