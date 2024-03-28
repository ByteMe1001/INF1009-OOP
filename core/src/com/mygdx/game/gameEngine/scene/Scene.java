package com.mygdx.game.gameEngine.scene;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.sound.SoundManager;


public abstract class Scene implements Screen {

    // Manager Variables
    private SceneManager sceneManager;
    private EntityManager entityManager;
    private SoundManager soundManager;

    // Screen variables
    private Camera camera;
    private Viewport viewport;

    //Graphics variables
    private SpriteBatch batch;
    private Texture background;

    //Timing
    private int backgroundOffset;

    //World
    private int WORLD_WIDTH = 640;
    private int WORLD_HEIGHT = 640;

    private boolean isPaused;

    public Scene(SceneManager sceneManager, EntityManager entityManager, SoundManager soundManager, SpriteBatch batch) {
        this.sceneManager = sceneManager;
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        isPaused = false;

        this.batch = batch;

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        backgroundOffset = 0;
    }

    public Scene(SceneManager sceneManager, SoundManager soundManager, SpriteBatch batch) {
    }


    @Override
    public void dispose(){
        getBackground().dispose();
    }

    public void show() {
        // Empty for override
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground (Texture background) {
        this.background = background;
    }


    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {
        sceneManager.pause();
    }

    @Override
    public void resume() {
        sceneManager.resume();

    }

    @Override
    public void hide() {

    }

    // Getter Setter methods
    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public int getBackgroundOffset() {
        return backgroundOffset;
    }

    public void setBackgroundOffset(int backgroundOffset) {
        this.backgroundOffset = backgroundOffset;
    }

    public int getWORLD_WIDTH() {
        return WORLD_WIDTH;
    }

    public void setWORLD_WIDTH(int WORLD_WIDTH) {
        this.WORLD_WIDTH = WORLD_WIDTH;
    }

    public int getWORLD_HEIGHT() {
        return WORLD_HEIGHT;
    }

    public void setWORLD_HEIGHT(int WORLD_HEIGHT) {
        this.WORLD_HEIGHT = WORLD_HEIGHT;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

}

