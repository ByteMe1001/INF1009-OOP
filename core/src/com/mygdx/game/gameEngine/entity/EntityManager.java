package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.game.gameLayer.entity.Boss;
import com.mygdx.game.gameLayer.entity.Enemy;
import com.mygdx.game.gameLayer.entity.EntityFactory;
import com.mygdx.game.gameLayer.entity.EntityType;

public class EntityManager {

    private CollisionManager collisionManager;
    private ArrayList<Entity> entityList;
    private ArrayList<iAiMovement> aiEntityList;
    private ArrayList<iPlayerMovement> playerEntityList;
    private ArrayList<iCollision> collisionList;
    private ArrayList<Entity> newEntityList;
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
        this.newEntityList = new ArrayList<>();
        this.collisionManager = new CollisionManager(this, soundManager, collisionList, collisionHandler);
        this.playerControlManager = new PlayerControlManager(this, playerEntityList);
        this.aiControlManager = new AiControlManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this, aiEntityList);;
        this.entityFactory = new EntityFactory(batch, this);
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
        this.newEntityList = new ArrayList<>();
        //~~~~~~~~~~~MANAGER CREATION~~~~~~~~~~~~~~~~~~~~~~~~
        this.collisionManager = new CollisionManager(this, soundManager, collisionList, collisionHandler);
        this.playerControlManager = new PlayerControlManager(this, playerEntityList);
        this.aiControlManager = new AiControlManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this, aiEntityList);
        this.entityFactory = new EntityFactory(batch, this);
    }

    // Main testing creation
    // Entity creations
    public void createPlayer() {
        Entity bucket = entityFactory.createEntity(EntityType.BOY); // Use factory to create bucket entity
        // addEntity(bucket);
        //System.out.println(collisionList.size());
    }


    public void createPlayers(int x) {
        for (int i = 0; i < x; i++) {
            Entity bucket = entityFactory.createEntity(EntityType.BOY); // Use EntityFactory to create bucket entity
        }
    }

    public void createEnemy() {
        Entity droplet = entityFactory.createEntity(EntityType.ENEMY); // Use factory to create droplet entity
    }

    // Main testing creation
    public void createEnemies(int x) {
        for (int i = 0; i < x; i++) {
        	// spawn at random parts of the screen in the top half
        	float randomX = random.nextFloat() * Gdx.graphics.getWidth();
            float randomY = (Gdx.graphics.getHeight() / 2) + random.nextFloat() * (Gdx.graphics.getHeight() / 2);
            Entity droplet = entityFactory.createEntity(EntityType.ENEMY);
            droplet.setX(randomX);
            droplet.setY(randomY);
           // Entity droplet = entityFactory.createEntity(EntityType.BOSS); // Use EntityFactory to create droplet entity
        }
    }

    public void createBoss() {
        Entity droplet = entityFactory.createEntity(EntityType.BOSS); // Use factory to create droplet entity
    }

    // Main testing creation
    public void createBosses(int x) {
        for (int i = 0; i < x; i++) {
            // spawn at random parts of the screen in the top half
            float randomX = random.nextFloat() * Gdx.graphics.getWidth();
            float randomY = (Gdx.graphics.getHeight() / 2) + random.nextFloat() * (Gdx.graphics.getHeight() / 2);
            Entity droplet = entityFactory.createEntity(EntityType.BOSS);
            droplet.setX(randomX);
            droplet.setY(randomY);
            // Entity droplet = entityFactory.createEntity(EntityType.BOSS); // Use EntityFactory to create droplet entity
        }
    }
    
    // To check if boss entities are all dead
    public boolean areAllEnemiesDead() {
        for (Entity entity : entityList) {
            if (entity instanceof Enemy && entity.isAlive()) {
                return false;  // If boss is alive, return false
            }
        }
        return true;  // If bosses are dead, return true
    }

    public boolean areAllBossesDead() {
        for (Entity entity : entityList) {
            if (entity instanceof Boss && entity.isAlive()) {
                return false;  // If boss is alive, return false
            }
        }
        return true;  // If bosses are dead, return true
    }

    public void createPlayerBullets(int x) {
        for (int i = 0; i < x; i++) {
            Entity bullet = entityFactory.createEntity(EntityType.BULLET); // Use EntityFactory to create bullet entity
        }
    }

    public void createEnemyBullets(int x){
        for (int i = 0; i < x; i++) {
            Entity bullet = entityFactory.createEntity(EntityType.ENEMYBULLET); // Use EntityFactory to create bullet entity
        }
    }
    
    public void createHealthPack(int x){
        for (int i = 0; i < x; i++) {
            Entity healthPack = entityFactory.createEntity(EntityType.HEALTHPACK); // Use EntityFactory to create healthpack entity
        }
    }

    // Maybe can use this function to clear all list?
    public void deleteEntity(Entity e) {
        if (!entityList.remove(e) && !playerEntityList.remove((iPlayerMovement) e) && !aiEntityList.remove((iAiMovement) e)) {
            throw new IllegalArgumentException("Entity not found in any entity list");
        }
    }

    //Add
    public void addEntity(Entity entity) {

        // Error handling
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        newEntityList.add(entity);

        boolean addedToList = false;

        if (entity instanceof iCollision && collisionList != null) {
            collisionList.add((iCollision) entity); // Adding to the collision list
//            System.out.println("Added to collision list");
            addedToList = true;
        }

        if (entity instanceof iAiMovement && aiEntityList != null) {
            aiEntityList.add((iAiMovement) entity); // Adding to the AI movement list
//            System.out.println("Added to AI movement list");
            addedToList = true;
        }

        if (entity instanceof iPlayerMovement && playerEntityList != null) {
            playerEntityList.add((iPlayerMovement) entity); // Adding to the player movement list
//            System.out.println("Added to player movement list");
            addedToList = true;
        }

        if (!addedToList) {
            throw new IllegalArgumentException("Entity does not implement any supported interfaces");
        }
    }

    // For loop to draw all entities
    public void draw() {
        ArrayList<Entity> copyOfEntityList = new ArrayList<>(entityList);
        for (Entity entity : copyOfEntityList) {
            entity.draw();
        }
    }

    public void update() {
        updateEntities(entityList);
        entityList.addAll(newEntityList);
        newEntityList.clear();
//        updateEntities(playerEntityList);
//        updateEntities(aiEntityList);
//        collisionManager.updateCollisionList(entityList, playerEntityList, aiEntityList);
        playerControlManager.update(this);
        aiControlManager.update();
        collisionManager.detectCollision(this);
    }

    private void updateEntities(ArrayList<Entity> entityList) {
        Iterator<Entity> iterator = entityList.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update();
            if (!entity.isAlive()) {
                iterator.remove();
            }
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

}
