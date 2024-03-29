package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.ai.AIControlManager;
import com.mygdx.game.gameEngine.collision.CollisionManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.mygdx.game.gameEngine.player.AbstractGamePlayerManager;
import com.mygdx.game.gameEngine.player.PlayerManager;
import com.mygdx.game.gameLayer.GamePlayerManager;
import com.mygdx.game.gameEngine.sound.SoundManager;
import com.mygdx.game.gameEngine.util.iAiMovement;
import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameLayer.collision.CollisionHandler;
import com.mygdx.game.gameLayer.entity.*;

public class EntityManager {

    private CollisionManager collisionManager;
    private PlayerManager playerManager;
    private ArrayList<Entity> entityList;
    private ArrayList<iAiMovement> aiEntityList;
    private ArrayList<PlayableEntity> playerEntityList;
    private ArrayList<iCollision> collisionList;
    private ArrayList<Entity> newEntityList;
    private EntityFactory entityFactory;
    private SpriteBatch batch;
    private AbstractGamePlayerManager abstractGamePlayerManager;
    private AIControlManager aiControlManager;
    private CollisionHandler collisionHandler;

    Random random = new Random();

    private static SoundManager soundManager;

    //Default constructor
    public EntityManager() {
    // do nothing
    }

    // Constructor with PlayerManager and factory
    public EntityManager(PlayerManager playerManager, SpriteBatch batch) {
        this.playerManager = playerManager;
        soundManager = SoundManager.getInstance();
        this.batch = batch;
        //~~~~~~~~~~~LIST CREATION~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.entityList = new ArrayList<Entity>();
        this.playerEntityList = new ArrayList<PlayableEntity>();
        this.aiEntityList = new ArrayList<iAiMovement>();
        this.collisionList = new ArrayList<iCollision>();
        this.newEntityList = new ArrayList<>();
        //~~~~~~~~~~~MANAGER CREATION~~~~~~~~~~~~~~~~~~~~~~~~
        this.collisionManager = new CollisionManager(playerManager, collisionList);
        this.abstractGamePlayerManager = new GamePlayerManager(playerEntityList);
        this.aiControlManager = new AIControlManager(aiEntityList);
    }

    public void deleteEntity(Entity e) {
        if (!entityList.remove(e)
                && !collisionList.remove((iCollision) e)
                && !playerEntityList.remove((PlayableEntity) e)
                && !aiEntityList.remove((iAiMovement) e)) {
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
            addedToList = true;
        }

        if (entity instanceof iAiMovement && aiEntityList != null) {
            aiEntityList.add((iAiMovement) entity); // Adding to the AI movement list
            addedToList = true;
        }

        if (entity instanceof PlayableEntity && playerEntityList != null) {
            playerEntityList.add((PlayableEntity) entity); // Adding to the player movement list
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
        abstractGamePlayerManager.update(this);
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
                if (entity instanceof iCollision) {
                    collisionList.remove(entity);
                }
                if (entity instanceof PlayableEntity) {
                    playerEntityList.remove(entity);
                }
                if (entity instanceof iAiMovement) {
                    aiEntityList.remove(entity);
                }
            }
        }
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

    public AbstractGamePlayerManager getAbstractGamePlayerManager() {
        return abstractGamePlayerManager;
    }

    public void setAbstractGamePlayerManager(AbstractGamePlayerManager abstractGamePlayerManager) {
        this.abstractGamePlayerManager = abstractGamePlayerManager;
    }


    public AIControlManager getAiControlManager() {
        return aiControlManager;
    }

    public void setAiControlManager(AIControlManager aiControlManager) {
        this.aiControlManager = aiControlManager;
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

    public boolean isPlayerDead() {
        for (Entity entity : entityList) {
            if (entity instanceof Boy && entity.isAlive()) {
                return false;  // If player is alive, return false
            }
        }
        return true;  // If player is dead, return true
    }

    public boolean areAllBossesDead() {
        for (Entity entity : entityList) {
            if (entity instanceof Boss && entity.isAlive()) {
                return false;  // If boss is alive, return false
            }
        }
        return true;  // If bosses are dead, return true
    }

    public float getSpriteWidth(Entity e) {
        return findEntity(e).getSpriteHeight();
    }

    public float getSpriteHeight(Entity e) {
        return findEntity(e).getSpriteWidth();
    }

    // Method to find entity
    private Entity findEntity(Entity e) {
        for (Entity entity : entityList) {
            if (entity == e) {
                return entity;
            }
        }

        throw new IllegalArgumentException("Entity not found in the entity list");
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

}
