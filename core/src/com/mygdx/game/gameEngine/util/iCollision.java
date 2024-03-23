package com.mygdx.game.gameEngine.util;

import com.mygdx.game.gameEngine.entity.Entity;
import com.badlogic.gdx.math.Rectangle;

public interface iCollision {

    public boolean collidesWith(iCollision entity) ;


    public Rectangle getBoundingBox();

}

