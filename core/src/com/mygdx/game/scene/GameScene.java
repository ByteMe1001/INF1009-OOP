package com.mygdx.game.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.util.SoundManager;
import com.mygdx.game.entity.EntityManager;

class GameScene extends Scene{


    private final static String TEXTURE_PATH = "Space.jpg";

    protected GameScene(SceneManager sceneManager, EntityManager entityManager, SoundManager soundManager, SpriteBatch batch) {
        super(sceneManager,entityManager, soundManager, batch);
        //this.entityManager = new EntityManager(soundManager, batch);        // Can put here or show()
    }

    @Override
    public void show() {
        super.setBackground(new Texture(TEXTURE_PATH));
        if (!super.getSoundManager().isMusicPlaying()) {
            super.getSoundManager().playMusic("GameScene");
        }
        super.getEntityManager().createBucket();
        super.getEntityManager().createDroplets(5);
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1);      // Clear screen

        // Render logic here
        super.getBatch().begin();
        // Background code
        super.getBatch().draw(super.getBackground(), 0, 0, 640,640);
        // Entity Manager codes
        super.getEntityManager().update();
        super.getEntityManager().draw();
        super.getBatch().end();
    }

    @Override
    public void dispose() {
        // Dispose of resources
        super.getEntityManager().dispose();
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
