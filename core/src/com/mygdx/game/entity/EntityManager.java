package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.ai.AiControlManager;
import com.mygdx.game.collision.CollisionManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.mygdx.game.player.PlayerControlManager;
import com.mygdx.game.util.SoundManager;

public class EntityManager {

    private CollisionManager collisionManager;
    private ArrayList<Entity> entityList;
    private ArrayList<Entity> aiEntityList;
    private ArrayList<Entity> playerEntityList;
    //private AiManager aiManager;
    private SpriteBatch batch;

    private PlayerControlManager playerControlManager;
    private AiControlManager aiControlManager;

    EntityManager entityManager;

    Random random = new Random();

    private SoundManager soundManager;

    // REMEMBER to dispose texture

    //Default constructor
    public EntityManager() {
    // do nothing dont touch
    }

    //Constructor with collisionManager as an instance variable
    public EntityManager(SoundManager soundManager, SpriteBatch batch) {
        this.soundManager = soundManager;
        this.batch = batch;
        this.entityList = new ArrayList<Entity>();
        this.playerEntityList = new ArrayList<Entity>();
        this.aiEntityList = new ArrayList<Entity>();
        this.collisionManager = new CollisionManager(this, soundManager, entityList, playerEntityList, aiEntityList);
        this.playerControlManager = new PlayerControlManager();
        this.aiControlManager = new AiControlManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this, aiEntityList);
    }

     // Constructor with collisionManager and aiManager as instance variables
//    public EntityManager(CollisionManager collisionManager, AiControlManager aiControlManager) {
//        entityList = new ArrayList<>();
//        this.collisionManager = collisionManager;
//        this.aiControlManager = aiControlManager;  // Step 3: Initialize AiManager
//    }

    public void createBucket() {
        Bucket bucket = new Bucket(playerControlManager,1, 100, 50f, 100f, 1.0f, 64f, 64f, 300, 3, batch);
        addEntity(bucket);
    }

    public void createDroplet() {
        Droplet droplet = new Droplet(aiControlManager,1, 100, 300.0f, 100.0f, 1.0f, 64f, 64f, 300, 3, batch);
        addEntity(droplet);
    }

    public void createDroplets(int x) {
        for (int i = 0; i < x; i++) {
            float randomX = random.nextInt(640);
            float randomY = random.nextInt(640);
            Droplet droplet = new Droplet(aiControlManager, 1, 100, randomX, randomY, 1.0f, 64f, 64f, 300, 3, batch);
            addEntity(droplet);
        }
    }

    // maybe to use enum?
    public void createEntities(int player, int ai, int entity) {
        for (int i = 0; i < player; i++){
            Bucket bucket = new Bucket(playerControlManager,1, 100, 300.0f, 100.0f, 1.0f, 50f, 50f, 10, 10, batch); //not sure
        }
    }

    public void createEntities (int player, int ai) {
        for (int i = 0; i < player; i++){
            Bucket bucket = new Bucket(playerControlManager, 1, 100, 300.0f, 100.0f, 1.0f, 50f, 50f, 10, 10, batch); //not sure
        }

        for (int i = 0; i < player; i++){
            Droplet droplet = new Droplet(aiControlManager,1, 100, 300.0f, 100.0f, 1.0f, 64f, 64f, 300, 3, batch); //not sure
        }
    }

    public void deleteEntity(Entity e) {
        if (!entityList.remove(e) && !playerEntityList.remove(e) && !aiEntityList.remove(e)) {
            throw new IllegalArgumentException("Entity not found in any entity list");
        }
    }

    //Add
    public void addEntity(Entity entity) {
        if (entity.getControl() == null) {
            entityList.add(entity);
        }
        else if (entity.getControl() == 'A') {
            aiEntityList.add(entity);
        }
        else if (entity.getControl() == 'P') {
            playerEntityList.add(entity);
        }
    }

    //Iterates through entityList and calls their update method
    public void updateEntityList() {
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

    // For loop to draw all entities
    public void draw() {
        for (Entity entity : entityList) {
            entity.draw();
        }
        for (Entity entity : playerEntityList) {
            entity.draw();
        }
        for (Entity entity : aiEntityList) {
            entity.draw();
        }
    }

    public void update() {
        for (Entity entity : entityList) {
                entity.update();
        }

        for (Entity entity : playerEntityList) {
            entity.update();
        }

        for (Entity entity : aiEntityList) {
            entity.update();
        }

        collisionManager.updateCollisionList(entityList, playerEntityList, aiEntityList);
        collisionManager.detectCollision(this);
    }

    public void dispose() {
        // for loop to dispose entityList
        for (Entity entity : entityList) {
            entity.dispose();
        }
        entityList.clear(); // Clear the entity list after disposing them
    }

    public float getSpeed(Entity e) {
        return findEntity(e).getSpeed();
    }

    public float getX(Entity e) {
        return findEntity(e).getX();
    }

    public float getY(Entity e) {
        return findEntity(e).getY();
    }

    public void setX(Entity e, float x) {
        findEntity(e).setX(x);
    }

    public void setY(Entity e, float y) {
        findEntity(e).setY(y);
    }

    public boolean getIsCollidable(Entity e) {
        return findEntity(e).isCollidable();
    }

    public void setIsCollidable(Entity e, boolean b) {
        findEntity(e).setCollidable(b);
    }

    public int getChangeRate(Entity e) {
        return findEntity(e).getChangeRate();
    }

    public int getDefaultChangeRate(Entity e) {
        return findEntity(e).getDefaultChangeRate();
    }

    public void setChangeRate(Entity e, int x) {
        findEntity(e).setChangeRate(x);
    }

    public void decrementChangeRate(Entity e) {
        Entity entity = findEntity(e);
        entity.setChangeRate(entity.getChangeRate() - 1);
    }

    public int getDirection(Entity e) {
        return findEntity(e).getDirection();
    }

    public String getCurrentDirection(Entity e) {
        return findEntity(e).getCurrentDirection();
    }

    public void setCurrentDirection(Entity e, String s) {
            findEntity(e).setCurrentDirection(s);
    }

    public float getSpriteWidth(Entity e) {
        return findEntity(e).getSpriteHeight();
    }

    public float getSpriteHeight(Entity e) {
        return findEntity(e).getSpriteWidth();
    }

    public Rectangle getBoundingBox(Entity e) {
        return findEntity(e).getBoundingBox();
    }

    public Character getControl(Entity e) {
        return findEntity(e).getControl();
    }

    private Entity findEntity(Entity e) {
        for (Entity entity : entityList) {
            if (entity == e) {
                return entity;
            }
        }
        for (Entity entity : playerEntityList) {
            if (entity == e) {
                return entity;
            }
        }
        for (Entity entity : aiEntityList) {
            if (entity == e) {
                return entity;
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }
}



