package com.mygdx.game.util;

import com.badlogic.gdx.Preferences;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.List;

public interface iCollision {

    boolean collidesWith(Entity other) ;


    Rectangle getBoundingBox();
    //public void checkCollision(EntityManager entityManager);


}

