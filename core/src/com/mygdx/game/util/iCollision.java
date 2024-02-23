package com.mygdx.game.util;

import com.badlogic.gdx.Preferences;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

import java.util.List;

public interface iCollision {

    default boolean collidesWith(EntityManager entityManager, Entity entity, Entity other) {
        return entityManager.getBoundingBox(entity).overlaps(entityManager.getBoundingBox(other));//if entity's bounding box overlaps another entities bounding box
    }
    //public void checkCollision(EntityManager entityManager);

    default void handleCollision(EntityManager entityManager, SoundManager soundManager, List<Entity> collisionList, Entity x, Entity y){

        if(checkSameControl(entityManager, x, y)){
            System.out.println("Boing Boing"); // DEMO ONLY if entity control type is the same, print message on collide
//            collisionList.remove(y); //update collidable List to remove entity
//            entityManager.setIsALive(y, false); //set entity is alive to false
        }
        else {
            soundManager.playSE("GameScene_Collision");     // Player collision sound
            System.out.println("Asteroid Killed!");     // DEMO ONLY
            // collisionList.remove(x); //update collidableList to remove entity
            // entityManager.deleteEntity(x); //update entityManager to remove entity

            collisionList.remove(y); //update collidable List to remove entity
            entityManager.setIsALive(y, false); //set entity is alive to false
            // entityManager.deleteEntity(y); alternative logic to delete entity instead
            // entityManager.removeEntity(x); //update entityManager to remove entity from entity list
        }
    };

    default boolean checkSameControl(EntityManager entityManager, Entity entity, Entity other){
        return entityManager.getControl(entity) == entityManager.getControl(other); //if same entity control type is the same
    }
}

