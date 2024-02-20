package com.mygdx.game.scene;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity;
import com.mygdx.game.EntityManager;
import com.mygdx.game.scene.SceneManager;

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

    public void TextureObject(String t, float x, float y, float speed, boolean isAIControlled) {
    }
    public abstract void dispose();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}

