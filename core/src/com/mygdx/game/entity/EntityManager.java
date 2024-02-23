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

    private SpriteBatch batch;

    private PlayerControlManager playerControlManager;
    private AiControlManager aiControlManager;

    EntityManager entityManager;

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
        this.playerEntityList = new ArrayList<Entity>();
        this.aiEntityList = new ArrayList<Entity>();
        this.collisionManager = new CollisionManager(this, soundManager, entityList, playerEntityList, aiEntityList);
        this.playerControlManager = new PlayerControlManager();
        this.aiControlManager = new AiControlManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this, aiEntityList);
    }

    // Entity creations
    public void createBucket() {
        Bucket bucket = new Bucket(playerControlManager,BucketType.DEFAULT.getId(), BucketType.DEFAULT.getHealth(),
                BucketType.DEFAULT.getX(), BucketType.DEFAULT.getY(), BucketType.DEFAULT.getScale(),
                BucketType.DEFAULT.getWidth(), BucketType.DEFAULT.getHeight(), BucketType.DEFAULT.getSpeed(),
                BucketType.DEFAULT.getDirection(), batch);
        addEntity(bucket);
    }

    public void createBuckets(int x) {
        for (int i = 0; i < x; i++) {
            float randomX = random.nextInt(640);
            float randomY = random.nextInt(640);
            Bucket bucket = new Bucket(playerControlManager, BucketType.DEFAULT.getId(), BucketType.DEFAULT.getHealth(),
                    BucketType.DEFAULT.getX(), BucketType.DEFAULT.getY(), BucketType.DEFAULT.getScale(),
                    BucketType.DEFAULT.getWidth(), BucketType.DEFAULT.getHeight(), BucketType.DEFAULT.getSpeed(),
                    BucketType.DEFAULT.getDirection(), batch);
            addEntity(bucket);
        }
    }

    public void createDroplet(){
        Droplet droplet = new Droplet(aiControlManager,DropletType.DEFAULT.getId(), DropletType.DEFAULT.getHealth(),
                DropletType.DEFAULT.getX(), DropletType.DEFAULT.getY(), DropletType.DEFAULT.getScale(),
                DropletType.DEFAULT.getWidth(), DropletType.DEFAULT.getHeight(), DropletType.DEFAULT.getSpeed(),
                DropletType.DEFAULT.getDirection(), batch);
        addEntity(droplet);
    }

    public void createDroplets(int x) {
        for (int i = 0; i < x; i++) {
            float randomX = random.nextInt(Gdx.graphics.getHeight());
            float randomY = random.nextInt(Gdx.graphics.getWidth());
            Droplet droplet = new Droplet(aiControlManager,DropletType.DEFAULT.getId(), DropletType.DEFAULT.getHealth(),
                    randomX, randomY, DropletType.DEFAULT.getScale(),
                    DropletType.DEFAULT.getWidth(), DropletType.DEFAULT.getHeight(), DropletType.DEFAULT.getSpeed(),
                    DropletType.DEFAULT.getDirection(), batch);
            addEntity(droplet);
        }
    }

    // Create entity loops
    public void createEntities (int player, int ai) {
        createBuckets(player);
        createDroplets(ai);
    }

    public void createEntities(int player, int ai, int entity) {
       createBuckets(player);
       createDroplets(ai);
       //Create not moving droplet
        for (int i = 0; i < entity; i++){
            Droplet droplet = new Droplet(aiControlManager, i, 100, 300.0f, 200.0f, 1.0f, 50f, 50f, 10, 0, batch);
            addEntity(droplet);
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
        updateEntities(entityList);
        updateEntities(playerEntityList);
        updateEntities(aiEntityList);
        collisionManager.updateCollisionList(entityList, playerEntityList, aiEntityList);
        collisionManager.detectCollision(this);
    }

    private void updateEntities(ArrayList<Entity> entities) {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            if (!entity.isAlive()) {
                iterator.remove(); // Remove the entity from the list
                continue; // Skip to the next iteration if the entity is not alive
            }
            entity.update();
        }
    }

    public void dispose() {
        // Dispose entities in entityList
        for (Entity entity : entityList) {
            entity.dispose();
        }
        // Clear entityList
        entityList.clear();

        // Dispose entities in playerEntityList
        for (Entity entity : playerEntityList) {
            entity.dispose();
        }
        // Clear playerEntityList
        playerEntityList.clear();

        // Dispose entities in aiEntityList
        for (Entity entity : aiEntityList) {
            entity.dispose();
        }
        // Clear aiEntityList
        aiEntityList.clear();
    }

    // Getter Setter methods
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

    public boolean getIsAlive(Entity e) {
        return findEntity(e).isAlive();
    }

    public void setIsALive(Entity e, boolean b) {
        findEntity(e).setAlive(b);
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

    // Method to find entity
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



