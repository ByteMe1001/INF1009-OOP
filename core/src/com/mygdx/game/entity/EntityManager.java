package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AiControlManager;
import com.mygdx.game.CollisionManager;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mygdx.game.entity.Bucket;
import com.mygdx.game.entity.Droplet;

public class EntityManager {

    private CollisionManager collisionManager;
    private ArrayList<Entity> entityList;
    //private AiManager aiManager;
    private SpriteBatch batch;

    // REMEMBER to dispose texture

    //Default constructor
    public EntityManager() {
    // do nothing dont touch
    }

    //Constructor with collisionManager as an instance variable
    public EntityManager(CollisionManager collisionManager, SpriteBatch batch) {
        entityList = new ArrayList<Entity>();
        this.collisionManager = collisionManager;
        this.batch = batch;
    }

     // Constructor with collisionManager and aiManager as instance variables
//    public EntityManager(CollisionManager collisionManager, AiControlManager aiControlManager) {
//        entityList = new ArrayList<>();
//        this.collisionManager = collisionManager;
//        this.aiControlManager = aiControlManager;  // Step 3: Initialize AiManager
//    }

    public void createBucket() {
        Bucket bucket = new Bucket(1, 100, 300.0f, 100.0f, 1.0f, 50, 50, 50, batch);
        addEntity(bucket);
    }

    public void createDroplet() {
        Droplet droplet = new Droplet(1, 100, 300.0f, 100.0f, 1.0f, 50, 50, 50, batch);
        addEntity(droplet);
    }


    // maybe to use enum?
    public void createEntities(int player, int ai, int entity) {
        for (int i = 0; i < player; i++){
            Bucket bucket = new Bucket(1, 100, 300.0f, 100.0f, 1.0f, 50, 50, 10, batch); //not sure
        }
    }

    public void createEntities (int player, int ai) {
        for (int i = 0; i < player; i++){
            Bucket bucket = new Bucket(1, 100, 300.0f, 100.0f, 1.0f, 50, 50, 10, batch); //not sure
        }

        for (int i = 0; i < player; i++){
            Bucket bucket = new Bucket(); //not sure
        }
    }



    //Add
    public void addEntity(Entity entity) {
        entityList.add(entity);
    }
    //Remove
    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }
    //Iterates through entityList and calls their update method
    public void updateentityList() {
        Iterator<Entity> iterator = entityList.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update();

            if (!entity.isAlive()) {
                entity = null;
                iterator.remove();
            }
        }
        //collisionManager.handleCollisions(entityList);
    }
    //Rendering method
//    public void renderentityList(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
//        for (Entity entity : entityList) {
//            entity.render(spriteBatch, shapeRenderer);
//        }
//    }
//            entity.render(spriteBatch, shapeRenderer);
//        }
//    }

    // For loop to draw all entities
    public void draw() {
        for (Entity entity : entityList) {
            entity.draw();
        }
    }

    public void update() {
        for (Entity entity : entityList) {
            if (entity instanceof Bucket){
                entity.update();
            }
        }
    }

    public void dispose() {
        // for loop to dispose entityList
    }
}



