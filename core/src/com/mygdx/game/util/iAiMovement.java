package com.mygdx.game.util;

public interface iAiMovement {
    // These methods should be implemented by entities to define how they react to movement instructions
    void setLeftRight();
    void setUpDown();
    void setAll();
}
