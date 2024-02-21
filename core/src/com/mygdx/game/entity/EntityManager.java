package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AiManager;
import com.mygdx.game.CollisionManager;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mygdx.game.entity.Bucket;
import com.mygdx.game.entity.Droplet;

public class EntityManager {

    private CollisionManager collisionManager;
    private List<Entity> entities;
    //private AiManager aiManager;
    private SpriteBatch spriteBatch;

    // REMEMBER to dispose texture

    //Default constructor
    public EntityManager() {
    // do nothing dont touch
    }

    //Constructor with collisionManager as an instance variable
    public EntityManager(CollisionManager collisionManager) {
        entities = new ArrayList<>();
        this.collisionManager = collisionManager;
    }

     // Constructor with collisionManager and aiManager as instance variables
    public EntityManager(CollisionManager collisionManager, AiManager aiManager) {
        entities = new ArrayList<>();
        this.collisionManager = collisionManager;
        this.aiManager = aiManager;  // Step 3: Initialize AiManager
    }

    public void createBucket() {
        Bucket bucket = new Bucket();
        addEntity(bucket);
    }

    public void createDroplet() {
        Droplet droplet = new Droplet();
        addEntity(droplet);
    }


    // maybe to use enum?
    public void createEntities(int player, int ai, int entity) {
        for (int i = 0; i < player; i++){
            Bucket bucket = new Bucket(); //not sure
        }
    }

    public void createEntities (int player, int ai) {
        for (int i = 0; i < player; i++){
            Bucket bucket = new Bucket(); //not sure
        }

        for (int i = 0; i < player; i++){
            Bucket bucket = new Bucket(); //not sure
        }
    }



    //Add
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    //Remove
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    //Iterates through entities and calls their update method
    public void updateEntities() {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update();

            if (!entity.isAlive()) {
                entity = null;
                iterator.remove();
            }
        }
        //collisionManager.handleCollisions(entities);
    }
    //Rendering method
//    public void renderEntities(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
//        for (Entity entity : entities) {
//            entity.render(spriteBatch, shapeRenderer);
//        }
//    }
//            entity.render(spriteBatch, shapeRenderer);
//        }
//    }
    // Updated dispose loop
    public void dispose() {
    for (Entity entity : entities) {
        entity.dispose();
    }
    entities.clear(); // Clear the entity list after disposing them
    }
}



