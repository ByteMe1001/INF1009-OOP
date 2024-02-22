package com.mygdx.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CollisionManager;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

// Name your class file properly
// an example of a scene that can be used in the game (test out main codes)
public class GameScene extends Scene{
    private final int NUM_DROPS = 10;
    //private Entity[] drops = new Entity[NUM_DROPS];
    //Random random = new Random();
    private EntityManager entityManager;;

    public GameScene(SceneManager sceneManager, SpriteBatch batch) {
        super(sceneManager, batch);
        this.entityManager = new EntityManager(new CollisionManager(), batch);        // Can put here or show()
        super.setBackground(new Texture("Space.jpg"));
        //Gdx.app.log("MyGDXGame", "Gamescene constructor!");

        // Create entities
        entityManager.createBucket();
        entityManager.createDroplet();
    }

    // Not needed
    @Override
    public void create() {
        entityManager = new EntityManager();
        // Create entities for this scene
        //addEntity(); // add in droplet and bucket accordingly idk
    }


    @Override
    public void render(float deltaTime) {
        // Clear the screen
        //ScreenUtils.clear(0, 0, 0, 1);
        super.getBatch().begin();

        // Background code
        super.getBatch().draw(super.getBackground(), 0, 0, 640,640);
        entityManager.update();
        entityManager.draw();
        //Gdx.app.log("MyGDXGame", "Gamescene render!");

        // EntityManager

        super.getBatch().end();

    }

    @Override
    public void dispose() {
        // Dispose of resources
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(int width, int height) {
    }



}
