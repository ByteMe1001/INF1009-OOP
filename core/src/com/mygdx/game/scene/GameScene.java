package com.mygdx.game.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.util.SoundManager;
import com.mygdx.game.entity.EntityManager;

// Name your class file properly
// an example of a scene that can be used in the game (test out main codes)
class GameScene extends Scene{
    private EntityManager entityManager;

    private final static String TEXTURE_PATH = "Space.jpg";

    public GameScene(SceneManager sceneManager, SoundManager soundManager, SpriteBatch batch) {
        super(sceneManager, soundManager, batch);
        this.entityManager = new EntityManager(soundManager, batch);        // Can put here or show()

        super.setBackground(new Texture(TEXTURE_PATH));
        entityManager.createBucket();
        entityManager.createDroplets(5);
        //Gdx.app.log("MyGDXGame", "Gamescene constructor!");
    }

    @Override
    public void show() {
        // Can put here or show()
        //super.setBackground(new Texture("Space.jpg"));
        System.out.println("Create");
        if (!super.getSoundManager().isMusicPlaying()) {
            super.getSoundManager().playMusic("GameScene");
        }
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
        super.getSoundManager().dispose();
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
