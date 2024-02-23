package com.mygdx.game.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.util.SoundManager;
import com.mygdx.game.entity.EntityManager;

// Name your class file properly
// an example of a scene that can be used in the game (test out main codes)
class GameScene extends Scene{
    private EntityManager entityManager;

    private final static String TEXTURE_PATH = "Space.jpg";

    protected GameScene(SceneManager sceneManager, SoundManager soundManager, SpriteBatch batch) {
        super(sceneManager, soundManager, batch);
        this.entityManager = new EntityManager(soundManager, batch);        // Can put here or show()
    }

    @Override
    public void show() {
        super.setBackground(new Texture(TEXTURE_PATH));
        if (!super.getSoundManager().isMusicPlaying()) {
            super.getSoundManager().playMusic("GameScene");
        }
        entityManager.createBucket();
        entityManager.createDroplets(5);
    }


    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1);      // Clear screen

        // Render logic here
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
        super.getSoundManager().stopAll();
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
