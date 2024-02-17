
package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class EntityManager {

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private final int NUM_DROPS = 10;
    private List<Entity> entityList;



    public EntityManager() {
        entityList = new ArrayList<>();
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        //initializeEntities();
    }

//    private void initializeEntities() {
//        Random random = new Random();
//
//        // Add drops to the list
//        for (int i = 0; i < NUM_DROPS; i++) {
//            int xPos = random.nextInt(600);
//            int speed = random.nextInt(50) + 20;  // Adjust the range as needed
//            entityList.add(new TextureObject("droplet.png", xPos, Gdx.graphics.getHeight(), speed, true));
//
//            // Add all the entities to the list
//            entityList.add(new TextureObject("bucket.png", 200, 0, 300, false));
//            entityList.add(new circle(300, 300, 300, 50, Color.RED));
//            entityList.add(new triangle(400, 100, 300, 90, 75, Color.GREEN));
//        }
//    }
//
//    public void update() {
//        for (Entity entity : entityList) {
//            if (entity instanceof TextureObject) {
//                TextureObject textureObject = (TextureObject) entity;
//                if (textureObject.AIControlled()) {
//                    textureObject.moveAIControlled();
//                } else {
//                    textureObject.moveUserControlled();
//                }
//            } else {
//                // Handle other types of entities if needed
//                entity.moveUserControlled();
//            }
//        }
//    }
//
//    public void draw() {
//        renderer.begin(ShapeRenderer.ShapeType.Filled);
//        for (Entity entity : entityList) {
//            entity.draw(renderer);
//        }
//        renderer.end();
//
//        batch.begin();
//        for (Entity entity : entityList) {
//            if (entity instanceof TextureObject) {
//                ((TextureObject) entity).draw(batch);
//            }
//        }
//        batch.end();
//    }

    public void dispose() {
        batch.dispose();
    }
}