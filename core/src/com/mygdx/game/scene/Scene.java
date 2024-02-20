package com.mygdx.game.scene;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;

// You need to implement scene!
public abstract class Scene implements Screen {
    private SceneManager sceneManager;
    private List<Entity> entities;
    // protected SoundManager sm; // to create a sound manager
    private EntityManager entityManager;

    public Scene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        entityManager = new EntityManager();
        entities = new ArrayList<>();
    }

    // to be implemented by subclasses
    public abstract void create();
    public abstract void update();
    //public abstract void render(SpriteBatch batch);

    public void TextureObject(String t, float x, float y, float speed, boolean isAIControlled) {
    }
    public void dispose(){
        entityManager.dispose();
    }

//    public void addEntity(Entity entity) {
//        entities.add(entity);
//    }

}

