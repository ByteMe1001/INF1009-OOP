package com.mygdx.game.util;

import com.badlogic.gdx.Preferences;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

import java.util.List;

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

    default boolean collidesWith(EntityManager entityManager, Entity entity, Entity other) {
        return entityManager.getBoundingBox(entity).overlaps(entityManager.getBoundingBox(other));//if entity's bounding box overlaps another entities bounding box
    }
    //public void checkCollision(EntityManager entityManager);

    default void handleCollision(EntityManager entityManager, SoundManager soundManager, List<Entity> collisionList, Entity x, Entity y){

        if(checkSameControl(entityManager, x, y)){
            System.out.println("Boing Boing");
        }
        else {
            //collisionList.remove(x); //update collidableList to remove entity
            //System.out.println(x + " has been removed from the collision list");
            //entityManager.removeEntity(x); //update entityManager to remove entity
            System.out.println("Collision Detected between " + x + " and " + y);
            soundManager.playSE("GameScene_Collision");

            collisionList.remove(y); //update collidableList to remove entity
            System.out.println(y + " has been removed from the collision list");
            entityManager.deleteEntity(y);
            System.out.println(y + " has been removed from the entity list");
            //entityManager.removeEntity(x); //update entityManager to remove entity from entity list

//            System.out.println("AI Entity Removed");
            //collidableList.remove(y); //update collidableList to remove entity
            //entityManager.removeEntity(y); //update entityManager to remove entity
//            System.out.println("AI Entity Removed");

        }
        System.out.println("Testing Collision Handler");
    };

    default boolean checkSameControl(EntityManager entityManager, Entity entity, Entity other){
        return entityManager.getControl(entity) == entityManager.getControl(other);
    }
}

