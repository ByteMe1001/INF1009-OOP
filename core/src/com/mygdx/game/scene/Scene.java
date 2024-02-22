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

// You need to implement scene!
public abstract class Scene implements Screen {

    //change to private
    protected SceneManager sceneManager;
    protected List<Entity> entityList;
    // protected SoundManager sm; // to create a sound manager
    protected EntityManager entityManager;

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

    public Scene(SceneManager sceneManager, SpriteBatch batch) {
        this.sceneManager = sceneManager;
        //this.entityManager = new EntityManager();
        this.entityList = new ArrayList<>();
        this.batch = batch;

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        //background = new Texture("Space.jpg");
        backgroundOffset = 0;
    }

    // Not inside scene interface
    // to be implemented by subclasses
    public abstract void create();
    //public abstract void update();
    //public abstract void render(SpriteBatch batch);

    public void TextureObject(String t, float x, float y, float speed, boolean isAIControlled) {
    }
    @Override
    public void dispose(){
        entityManager.dispose();
    }

//    public void addEntity(Entity entity) {
//        entityList.add(entity);
//    }

    public void show() {


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


    public void render(float delta, SpriteBatch batch) {

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

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public List<Entity> getentityList() {
        return entityList;
    }

    public void setentityList(List<Entity> entityList) {
        this.entityList = entityList;
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

}

