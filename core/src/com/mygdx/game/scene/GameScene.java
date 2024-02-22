package com.mygdx.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CollisionManager;
import com.mygdx.game.util.SoundManager;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

// Name your class file properly
// an example of a scene that can be used in the game (test out main codes)
public class GameScene extends Scene{
    private EntityManager entityManager;
    private SoundManager soundManager;

    public GameScene(SceneManager sceneManager, SpriteBatch batch) {
        super(sceneManager, batch);
        this.entityManager = new EntityManager(batch);        // Can put here or show()
        this.soundManager = new SoundManager();
        if (!soundManager.isMusicPlaying()) {
            soundManager.playMusic(1);
        }

        super.setBackground(new Texture("Space.jpg"));
        entityManager.createBucket();
        entityManager.createDroplets(5);
        //Gdx.app.log("MyGDXGame", "Gamescene constructor!");
    }

    // Not needed
    @Override
    public void create() {
        // Can put here or show()
        super.setBackground(new Texture("Space.jpg"));
        System.out.println("Create");
        // Create entities for this scene
        //addEntity(); // add in droplet and bucket accordingly idk
    }


    @Override
    public void render(float deltaTime) {
        super.getBatch().begin();
        // Background code
        super.getBatch().draw(super.getBackground(), 0, 0, 640,640);
        // Entity Manager codes
        entityManager.update();
        entityManager.draw();
        super.getBatch().end();
    }

    @Override
    public void dispose() {
        // Dispose of resources
        entityManager.dispose();
        soundManager.dispose();
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


}
