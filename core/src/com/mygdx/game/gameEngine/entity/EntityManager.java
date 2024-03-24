package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.ai.AiControlManager;
import com.mygdx.game.gameEngine.collision.CollisionManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.mygdx.game.gameEngine.player.PlayerControlManager;
import com.mygdx.game.gameEngine.util.SoundManager;
import com.mygdx.game.gameEngine.util.iAiMovement;
import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameEngine.util.iPlayerMovement;
import com.mygdx.game.gameLayer.collision.CollisionHandler;
import com.mygdx.game.gameLayer.entity.Boy;
import com.mygdx.game.gameLayer.entity.EntityType;

public class EntityManager {

    private CollisionManager collisionManager;
    private ArrayList<Entity> entityList;
    private ArrayList<iAiMovement> aiEntityList;
    private ArrayList<iPlayerMovement> playerEntityList;
    private ArrayList<iCollision> collisionList;
    private EntityFactory entityFactory;

    private SpriteBatch batch;

    private PlayerControlManager playerControlManager;
    private AiControlManager aiControlManager;
    private CollisionHandler collisionHandler;

    Random random = new Random();

    private SoundManager soundManager;

    //Default constructor
    public EntityManager() {
    // do nothing
    }

    //Constructor with collisionManager as an instance variable
    public EntityManager(SoundManager soundManager, SpriteBatch batch) {
        this.soundManager = soundManager;
        this.batch = batch;
        this.entityList = new ArrayList<Entity>();
        this.playerEntityList = new ArrayList<iPlayerMovement>();
        this.aiEntityList = new ArrayList<iAiMovement>();
        this.collisionList = new ArrayList<iCollision>();
        this.collisionManager = new CollisionManager(this, soundManager, collisionList, collisionHandler);
        this.playerControlManager = new PlayerControlManager(this, playerEntityList);
        this.aiControlManager = new AiControlManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this, aiEntityList);;
        this.entityFactory = new EntityFactory(batch);
    }

    // Constructor with EntityFactory
    public EntityManager(EntityFactory entityFactory, SoundManager soundManager, SpriteBatch batch) {
        this.entityFactory = entityFactory; // Initialize EntityFactory
        this.soundManager = soundManager;
        this.batch = batch;
        //~~~~~~~~~~~LIST CREATION~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.entityList = new ArrayList<Entity>();
        this.playerEntityList = new ArrayList<iPlayerMovement>();
        this.aiEntityList = new ArrayList<iAiMovement>();
        this.collisionList = new ArrayList<iCollision>();
        //~~~~~~~~~~~MANAGER CREATION~~~~~~~~~~~~~~~~~~~~~~~~
        this.collisionManager = new CollisionManager(this, soundManager, collisionList, collisionHandler);
        this.playerControlManager = new PlayerControlManager(this, playerEntityList);
        this.aiControlManager = new AiControlManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this, aiEntityList);
        this.entityFactory = new EntityFactory(batch);
    }

    // Main testing creation
    // Entity creations
    public void createBucket() {
        Entity bucket = entityFactory.createEntity(EntityType.BOY); // Use factory to create bucket entity
        addEntity(bucket);
        collisionList.add((iCollision) bucket);
        System.out.println(collisionList.size());
    }


    public void createBuckets(int x) {
        for (int i = 0; i < x; i++) {
            Entity bucket = entityFactory.createEntity(EntityType.BOY); // Use EntityFactory to create bucket entity
            addEntity(bucket);
            collisionList.add((iCollision) bucket);
        }
    }

    public void createDroplet() {
        Entity droplet = entityFactory.createEntity(EntityType.BOSS); // Use factory to create droplet entity
        addEntity(droplet);
    }

    // Main testing creation
    public void createDroplets(int x) {
        for (int i = 0; i < x; i++) {
            Entity droplet = entityFactory.createEntity(EntityType.BOSS); // Use EntityFactory to create droplet entity
            addEntity(droplet);
            collisionList.add((iCollision) droplet);
            aiEntityList.add((iAiMovement) droplet);
        }
    }

    public void createBullets(int x) {
        for (int i = 0; i < x; i++) {
            Entity bullet = entityFactory.createEntity(EntityType.BULLET); // Use EntityFactory to create bullet entity
            addEntity(bullet);
            collisionList.add((iCollision) bullet);
        }
    }

    // Create entity loops
    public void createEntities (int player, int ai) {
        createBuckets(player);
        createDroplets(ai);
    }

    // Maybe can use this function to clear all list?
    public void deleteEntity(Entity e) {
        if (!entityList.remove(e) && !playerEntityList.remove(e) && !aiEntityList.remove(e)) {
            throw new IllegalArgumentException("Entity not found in any entity list");
        }
    }

    //Add
    public void addEntity(Entity entity) {

        entityList.add(entity);
        if (entity instanceof iCollision) {
            collisionList.add((iCollision) entity); // Adding to the AI-controlled list
        }

        if (entity instanceof iAiMovement) {
            aiEntityList.add((iAiMovement) entity); // Adding to the AI-controlled list
        }

        if (entity instanceof iPlayerMovement) {
            playerEntityList.add((iPlayerMovement) entity); // Adding to the player-controlled list
        }
    }

    // For loop to draw all entities
    public void draw() {
        for (Entity entity : entityList) {
            entity.draw();
        }
    }

    public void update() {
        updateEntities(entityList);
//        updateEntities(playerEntityList);
//        updateEntities(aiEntityList);
//        collisionManager.updateCollisionList(entityList, playerEntityList, aiEntityList);
        playerControlManager.update(this);
        aiControlManager.update();
        collisionManager.detectCollision(this);
    }

    private void updateEntities(ArrayList<Entity> entities) {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            // TODO: See if we need this logic in the end
//            if (!entity.isAlive()) {
//                iterator.remove(); // Remove the entity from the list
//                continue; // Skip to the next iteration if the entity is not alive
//            }
            entity.update();
        }
    }

    public void dispose() {
        // Dispose entities in entityList FOR SPRITE
        for (Entity entity : entityList) {
            entity.dispose();
        }

        // Clear Lists
        entityList.clear();
        playerEntityList.clear();
        aiEntityList.clear();
    }

    // Getter Setter methods

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

    public boolean getIsAlive(Entity e) {
        return findEntity(e).isAlive();
    }

    public void setIsALive(Entity e, boolean b) {
        findEntity(e).setAlive(b);
    }

//    public boolean getIsCollidable(Entity e) {
//        return findEntity(e).isCollidable();
//    }
//
//    public void setIsCollidable(Entity e, boolean b) {
//        findEntity(e).setCollidable(b);
//    }
//
//    public float getSpeed(Entity e) {
//        return findEntity(e).getSpeed();
//    }

//    public int getChangeRate(Entity e) {
//        return findEntity(e).getChangeRate();
//    }
//
//    public int getDefaultChangeRate(Entity e) {
//        return findEntity(e).getDefaultChangeRate();
//    }

//    public void setChangeRate(Entity e, int x) {
//        findEntity(e).setChangeRate(x);
//    }

//    public void setDirection(Entity entity, int direction) {
//        entity.setDirection(direction);
//    }

//    public void decrementChangeRate(Entity e) {
//        Entity entity = findEntity(e);
//        entity.setChangeRate(entity.getChangeRate() - 1);
//    }

//    public int getDirection(Entity e) {
//        return findEntity(e).getDirection();
//    }
//
//    public String getCurrentDirection(Entity e) {
//        return findEntity(e).getCurrentDirection();
//    }
//
//    public void setCurrentDirection(Entity e, String s) {
//            findEntity(e).setCurrentDirection(s);
//    }

    public float getSpriteWidth(Entity e) {
        return findEntity(e).getSpriteHeight();
    }

    public float getSpriteHeight(Entity e) {
        return findEntity(e).getSpriteWidth();
    }

//    public Rectangle getBoundingBox(iCollision e) {
//        return findEntity(e).getBoundingBox();
//    }
//
//    public Character getControl(Entity e) {
//        return findEntity(e).getControl();
//    }

    // Method to find entity
    private Entity findEntity(Entity e) {
        for (Entity entity : entityList) {
            if (entity == e) {
                return entity;
            }
        }
//        for (Entity entity : playerEntityList) {
//            if (entity == e) {
//                return entity;
//            }
//        }
//        for (Entity entity : aiEntityList) {
//            if (entity == e) {
//                return entity;
//            }
//        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public Boy getBoyEntity() {
        for (Entity entity : entityList) {
            if (entity instanceof Boy) {
                return (Boy) entity;
            }
        }
        return null; // Return null if Boy entity is not found
    }
}
