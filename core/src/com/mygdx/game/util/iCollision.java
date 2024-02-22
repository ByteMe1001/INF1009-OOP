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
    //boolean collidesWith(Entity entity, Entity other);

    default boolean collidesWith(Entity entity, Entity other) {
        return entity.getBoundingBox().overlaps(other.getBoundingBox());//if entity's bounding box overlaps another entities bounding box
    }
    //public void checkCollision(EntityManager entityManager);

    default void handleCollision(EntityManager entityManager, Entity x, Entity y){
        //collidableList.remove(x); //update collidableList to remove entity
        entityManager.removeEntity(x); //update entityManager to remove entity
//            System.out.println("AI Entity Removed");
        //collidableList.remove(y); //update collidableList to remove entity
        //entityManager.removeEntity(y); //update entityManager to remove entity
//            System.out.println("AI Entity Removed");
//        }
        System.out.println("Testing Collision Handler");
    };
}

