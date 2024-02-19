package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene {
    protected SceneManager sceneManager;
    protected List<Entity> entities;
    // protected SoundManager sm; // to create a sound manager
    protected EntityManager em;

    public Scene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        entities = new ArrayList<>();
    }

    // to be implemented by subclasses
    public abstract void create();
    public abstract void update();
    public abstract void render(SpriteBatch batch);
    public Texture loadTexture(String path) {
        // to add other entities such as bucket
        em.addEntity(new TextureObject("droplet.png", xPosition, 50, speed, true)); // to change parameters
        return texture;
    }
    public abstract void dispose();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}

