package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {

    private CollisionManager collisionManager;
    private List<Entity> entities;
    private AiManager aiManager;
    private SpriteBatch spriteBatch;
    
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
}

//            entity.render(spriteBatch, shapeRenderer);
//        }
//    }


