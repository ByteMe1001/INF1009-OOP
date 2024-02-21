package com.mygdx.game.util;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

public interface iCollision {

    //public void enemyCollide();
    //public void playerCollide();

    //public boolean collidesWith();

    /*@Override
        public void enemyCollide() {
            if(entity.x == entity.x) {
                entity.destroy();
            }
        }

        @Override
        public void playerCollide() {

        }
        */
    boolean collidesWith(Entity other);
    public void detectCollisions(EntityManager entityManager);

    public void handleCollision(EntityManager entityManager, Entity x, EntityManager y);
}

