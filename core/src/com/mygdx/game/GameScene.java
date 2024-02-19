package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;

// an example of a scene that can be used in the game (test out main codes)
public class ExampleScene extends Scene {
    private final int NUM_DROPS = 10;
    //private Entity[] drops = new Entity[NUM_DROPS];
    //Random random = new Random();
    EntityManager em;;

    public ExampleScene(SceneManager sceneManager) {
        super(sceneManager);
    }

    @Override
    public void create() {
        em = new EntityManager();
        // Create entities for this scene
        addentity(); // add in droplet and bucket accordingly idk
    }

    @Override
    public void update() {
        // Update scene logic ?? im assuming the bucket movement?
        // might be wrong
        for (Entity entity : entities) {
            entity.update();
        }

        // Handle user input for the bucket (not sure if this is needed)
        float bucketSpeed = 5f;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            entities.get(1).setX(entities.get(1).getX() - bucketSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            entities.get(1).setX(entities.get(1).getX() + bucketSpeed);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        // Clear the screen
        ScreenUtils.clear(0, 0, 0, 1);

        // Begin batch rendering
        batch.begin();

        // Render each entity in the scene
        for (Entity entity : entities) {
            entity.draw(batch);
        }

        // End batch rendering
        batch.end();
    }

    @Override
    public void dispose() {
        // Dispose of resources
    }
}
