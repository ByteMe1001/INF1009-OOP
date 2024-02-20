package com.mygdx.game.util;

import com.mygdx.game.entity.Entity;

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
}

