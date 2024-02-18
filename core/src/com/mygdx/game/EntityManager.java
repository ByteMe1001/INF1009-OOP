package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {
    private List<Entity> entities;
    private CollisionManager collisionManager;

    // Default Constructornani
    public EntityManager() {

    }
    //Constructor with collisionManager as an instance variable
    public EntityManager(CollisionManager collisionManager) {
        entities = new ArrayList<>();
        this.collisionManager = collisionManager;
    }
    //Add
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    //Remove
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    //Iterates through entites and calls their update method
    public void updateEntities() {
        Iterator<Entity> iterator = entities.iterator();
//        while (iterator.hasNext()) {
//            Entity entity = iterator.next();
//            entity.update();
//
//            if (entity.isDestroyed()) {
//                iterator.remove();
//            }
//        }
//        // Handle collisions using the ConflictManager
//        collisionManager.handleCollisions(entities);
    }
    //Rendering method
//    public void renderEntities(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
//        for (Entity entity : entities) {
//            entity.render(spriteBatch, shapeRenderer);
//        }
//    }
}
