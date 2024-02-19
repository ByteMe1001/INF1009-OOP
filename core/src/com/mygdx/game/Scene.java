package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene {
    protected SceneManager sceneManager;
    protected List<Entity> entities;

    public Scene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        entities = new ArrayList<>();
    }

    // to be implemented by subclasses
    public abstract void create();
    public abstract void update();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
