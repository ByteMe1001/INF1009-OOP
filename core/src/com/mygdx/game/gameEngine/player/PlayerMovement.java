package com.mygdx.game.gameEngine.player;

import com.mygdx.game.gameEngine.util.Movement;
import com.mygdx.game.gameEngine.util.iPlayerMovement;

public abstract class PlayerMovement implements Movement {
    abstract public float[] calculateMovement(iPlayerMovement entity, String direction);
}
