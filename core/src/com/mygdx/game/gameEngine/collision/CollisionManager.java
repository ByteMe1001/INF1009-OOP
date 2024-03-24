package com.mygdx.game.gameEngine.collision;

import com.mygdx.game.gameEngine.entity.Entity;

import java.util.List;
import java.util.ArrayList;

import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.util.SoundManager;
import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameLayer.collision.CollisionHandler;


public class CollisionManager {

    private ArrayList<iCollision> collisionList;
    private EntityManager entityManager;     // Entity Manager for entity control
    private SoundManager soundManager;      // Sound manager for collision sounds
    private CollisionHandler collisionHandler;

    // private CollisionStrategy collisionStrategy;

    public CollisionManager(EntityManager entityManager, SoundManager soundManager, ArrayList<iCollision> collisionList, CollisionHandler collisionHandler ) {
        this.entityManager = entityManager;
        this.soundManager = new SoundManager();
        this.collisionList = collisionList;
        this.collisionHandler = new CollisionHandler();
    }

    // Default constructor
    public CollisionManager() {
        // Do nothing
    }

    // Detect collision through n! loop within collidable list to find boundingBox overlap
    public void detectCollision(EntityManager entityManager){
        for (int i = 0; i < collisionList.size() - 1; i++){
            for (int j = i + 1; j < collisionList.size(); j++){
                if(collisionList.get(i).collidesWith(collisionList.get(j))){ //If entities overlaps with one another, print Collision Detected in console
                    //handleCollision(entityManager, soundManager, collisionList, collisionList.get(i), collisionList.get(j)); //handles collision logic when entity collide
                    //System.out.println("Collision Detected");
                    collisionHandler.handleCollision(collisionList.get(i), collisionList.get(j));
                }
            }
        }
    }

//    // Refresh collision list
//    public void updateCollisionList(List<Entity> entityList , List<Entity> playerEntityList, List<Entity> aiEntityList) {
//        // Clear list first
//        //collisionList.clear();        // Alternative logic
//
//        for (Entity entity : entityList) {
//            if (entityManager.getIsCollidable(entity) != collisionList.contains(entity))
//                collisionList.add((iCollision) entity); //add collidable objects into entityList
//        }
//
//        for (Entity entity : playerEntityList) {
//            if (entityManager.getIsCollidable(entity) != collisionList.contains(entity))
//                collisionList.add((iCollision) entity); //add collidable objects into a playerEntityList
//        }
//        for (Entity entity : aiEntityList) {
//            if (entityManager.getIsCollidable(entity) != collisionList.contains(entity))
//                collisionList.add((iCollision) entity); //add collidable objects into a aiEntityList
//        }
//    }

    protected void handleCollision(EntityManager entityManager, SoundManager soundManager, List<iCollision> collisionList, iCollision x, iCollision y){

        // System.out.println("Boing Boing"); // DEMO ONLY if entity control type is the same, print message on collide
//            collisionList.remove(y); //update collidable List to remove entity
//            entityManager.setIsALive(y, false); //set entity is alive to false


        //}
        //else {
            soundManager.playSE("GameScene_Collision");     // Player collision sound
            System.out.println("Asteroid Killed!");     // DEMO ONLY
            // collisionList.remove(x); //update collidableList to remove entity
            // entityManager.deleteEntity(x); //update entityManager to remove entity



        soundManager.playSE("GameScene_Collision");     // Player collision sound
        System.out.println("Asteroid Killed!");     // DEMO ONLY
        // collisionList.remove(x); //update collidableList to remove entity
        // entityManager.deleteEntity(x); //update entityManager to remove entity

        collisionList.remove(y); //update collidable List to remove entity
        entityManager.setIsALive((Entity) y, false); //set entity is alive to false
        //entityManager.deleteEntity((Entity) y); //alternative logic to delete entity instead
        // entityManager.removeEntity(x); //update entityManager to remove entity from entity list
    }
}
































