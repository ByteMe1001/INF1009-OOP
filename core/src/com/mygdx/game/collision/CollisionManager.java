package com.mygdx.game.collision;

import com.mygdx.game.entity.Entity;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.util.SoundManager;
import com.mygdx.game.util.iCollision;


public class CollisionManager implements iCollision {

    private ArrayList<Entity> collisionList;
    private EntityManager entityManager;     // Entity Manager for entity control
    private SoundManager soundManager;      // Sound manager for collision sounds

    public CollisionManager(EntityManager entityManager, SoundManager soundManager, List<Entity> entityList, List<Entity> playerEntityList, List<Entity> aiEntityList) {
        this.entityManager = entityManager;
        this.soundManager = new SoundManager();
        collisionList = new ArrayList<Entity>();
    }

    // Default constructor
    public CollisionManager() {
        // Do nothing
    }

    // Detect collision through n! loop within collidable list to find boundingBox overlap
    public void detectCollision(EntityManager entityManager){
        for (int i = 0; i < collisionList.size() - 1; i++){
            for (int j = i + 1; j < collisionList.size(); j++){
                if(collidesWith(entityManager, collisionList.get(i), collisionList.get(j))){ //If entities overlaps with one another, print Collision Detected in console
                    handleCollision(entityManager, soundManager, collisionList, collisionList.get(i), collisionList.get(j)); //handles collision logic when entity collide
                }
            }
        }
    }

    // Refresh collision list
    public void updateCollisionList(List<Entity> entityList , List<Entity> playerEntityList, List<Entity> aiEntityList) {
        // Clear list first
        //collisionList.clear();        // Alternative logic

        for (Entity entity : entityList) {
            if (entityManager.getIsCollidable(entity) != collisionList.contains(entity))
                collisionList.add(entity); //add collidable objects into entityList
        }

        for (Entity entity : playerEntityList) {
            if (entityManager.getIsCollidable(entity) != collisionList.contains(entity))
                collisionList.add(entity); //add collidable objects into a playerEntityList
        }
        for (Entity entity : aiEntityList) {
            if (entityManager.getIsCollidable(entity) != collisionList.contains(entity))
                collisionList.add(entity); //add collidable objects into a aiEntityList
        }
    }
}
































