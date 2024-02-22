package com.mygdx.game.util;

import com.badlogic.gdx.Preferences;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

import java.util.List;

public interface iCollision {

    EntityManager entityManager = new EntityManager();

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

    default void handleCollision(EntityManager entityManager, List<Entity> collisionList, Entity x, Entity y){
        ;

        //collisionList.remove(x); //update collidableList to remove entity
        //System.out.println(x + " has been removed from the collision list");
        //entityManager.removeEntity(x); //update entityManager to remove entity

        collisionList.remove(y); //update collidableList to remove entity
        System.out.println(y + " has been removed from the collision list");
        entityManager.deleteEntity(y);
        System.out.println(y + " has been removed from the entity list");
        //entityManager.removeEntity(x); //update entityManager to remove entity from entity list

//            System.out.println("AI Entity Removed");
        //collidableList.remove(y); //update collidableList to remove entity
        //entityManager.removeEntity(y); //update entityManager to remove entity
//            System.out.println("AI Entity Removed");
//        }
        System.out.println("Testing Collision Handler");
    };
}

