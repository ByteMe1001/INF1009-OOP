package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.ENTITYDatatypeValidator;

public class CollisionManager extends EntityManager implements iCollision{

    private Entity entity;

    @Override
    public void enemyCollide() {
        if(entity.x == entity.x) {
            entity.destroy();
        }
    }

    @Override
    public void playerCollide() {

    }
}
