package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameMaster extends ApplicationAdapter {

    private EntityManager entityManager;

    @Override
    public void create() {
        entityManager = new EntityManager();
    }

   // @Override
//    public void render() {
//        ScreenUtils.clear(0, 0, 0.5f, 1);
//
//        // Call update and draw methods of the EntityManager
//        entityManager.update();
//        entityManager.draw();
//    }

    //@Override
//    public void dispose() {
//        entityManager.dispose();
//    }
}