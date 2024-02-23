package com.mygdx.game.scene;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.util.SoundManager;

// You need to implement scene!
public abstract class Scene implements Screen {

    //change to private
    private SceneManager sceneManager;
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

    public Scene(SceneManager sceneManager, SoundManager soundManager, SpriteBatch batch) {
        this.sceneManager = sceneManager;
        this.soundManager = soundManager;

        this.batch = batch;

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        backgroundOffset = 0;
    }

    @Override
    public void dispose(){
        getBackground().dispose();
    }

    public void show() {
        // Empty for override
    }

    protected Texture getBackground() {
        return background;
    }

    protected void setBackground (Texture background) {
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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    // Getter Setter methods
    protected SceneManager getSceneManager() {
        return sceneManager;
    }

    protected void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    protected Camera getCamera() {
        return camera;
    }

    protected void setCamera(Camera camera) {
        this.camera = camera;
    }

    protected int getBackgroundOffset() {
        return backgroundOffset;
    }

    protected void setBackgroundOffset(int backgroundOffset) {
        this.backgroundOffset = backgroundOffset;
    }

    protected int getWORLD_WIDTH() {
        return WORLD_WIDTH;
    }

    protected void setWORLD_WIDTH(int WORLD_WIDTH) {
        this.WORLD_WIDTH = WORLD_WIDTH;
    }

    protected int getWORLD_HEIGHT() {
        return WORLD_HEIGHT;
    }

    protected void setWORLD_HEIGHT(int WORLD_HEIGHT) {
        this.WORLD_HEIGHT = WORLD_HEIGHT;
    }

    protected SpriteBatch getBatch() {
        return this.batch;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

}

