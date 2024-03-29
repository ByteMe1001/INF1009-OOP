package com.mygdx.game.gameEngine.player;


import com.mygdx.game.gameEngine.util.iPlayerMovement;

// For Player Movement Strategy
public abstract class PlayerMovement {
    abstract public float[] calculateMovement(iPlayerMovement entity, String direction);

}
