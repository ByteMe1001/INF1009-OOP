package com.mygdx.game.GameEngine.Util;

import com.mygdx.game.GameEngine.Entity.Entity;
import com.badlogic.gdx.math.Rectangle;

public interface iCollision {

    boolean collidesWith(Entity other) ;


    Rectangle getBoundingBox();
    //public void checkCollision(EntityManager entityManager);


}

