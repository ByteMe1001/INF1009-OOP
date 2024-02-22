package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AiControlManager;
import com.mygdx.game.CollisionManager;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mygdx.game.PlayerControlManager;
import com.mygdx.game.entity.Bucket;
import com.mygdx.game.entity.Droplet;

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

    // REMEMBER to dispose texture

    //Default constructor
    public EntityManager() {
    // do nothing dont touch
    }

    //Constructor with collisionManager as an instance variable
    public EntityManager(CollisionManager collisionManager, SpriteBatch batch) {
        entityList = new ArrayList<Entity>();
        aiEntityList = new ArrayList<Entity>();
        this.collisionManager = new CollisionManager(entityList, aiEntityList);
        this.playerControlManager = new PlayerControlManager();
        this.aiControlManager = new AiControlManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this, aiEntityList);
        this.batch = batch;
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
        addAiEntity(droplet);
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
            Bucket bucket = new Bucket(); //not sure
        }
    }

    public void deleteEntity(Entity e) {
        for (Entity entity: entityList) {
            if (entity == e) {
                entityList.remove(entity);
                return;
            }
        }
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                aiEntityList.remove(entity);
                return;
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    //Add
    public void addEntity(Entity entity) {
        entityList.add(entity);
    }
    //Remove
    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }

    public void addAiEntity(Entity entity) {
        aiEntityList.add(entity);
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
        for (Entity entity : aiEntityList) {
            entity.draw();
        }
    }

    public void update() {
        for (Entity entity : entityList) {
            if (entity instanceof Bucket){
                entity.update();
            }
        }

        for (Entity entity : aiEntityList) {
            entity.update();
        }
        collisionManager.updateCollisionList(entityList, aiEntityList);
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
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                return entity.getSpeed();
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public float getX(Entity e) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                return entity.getX();
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public float getY(Entity e) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                return entity.getY();
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public void setX(Entity e, float x) {
        for (Entity entity: aiEntityList) {
            if (entity.equals(e)) {
                entity.setX(x);
                return;
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public void setY(Entity e, float y) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                entity.setY(y);
                return;
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public int getChangeRate(Entity e) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                return e.getChangeRate();
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public int getDefaultChangeRate(Entity e) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                return e.getDefaultChangeRate();
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public void setChangeRate(Entity e, int x) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                entity.setChangeRate(x);
                return;
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }


    public void decrementChangeRate(Entity e) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                entity.setChangeRate(entity.getChangeRate()-1);
                return;
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }


    public int getDirection(Entity e) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                return entity.getDirection();
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public String getCurrentDirection(Entity e) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                return entity.getCurrentDirection();
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }



    public void setCurrentDirection(Entity e, String s) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                entity.setCurrentDirection(s);
                return;
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public float getSpriteWidth(Entity e) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                return entity.getSpriteWidth();
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }

    public float getSpriteHeight(Entity e) {
        for (Entity entity: aiEntityList) {
            if (entity == e) {
                return entity.getSpriteWidth();
            }
        }
        throw new IllegalArgumentException("Entity not found in the entity list");
    }
}



