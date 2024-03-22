package com.mygdx.game.gameEngine.util;

import com.mygdx.game.gameEngine.entity.Entity;
import com.badlogic.gdx.math.Rectangle;

public interface iCollision {

    boolean collidesWith(Entity other) ;


    Rectangle getBoundingBox();
    //public void checkCollision(EntityManager entityManager);


}

