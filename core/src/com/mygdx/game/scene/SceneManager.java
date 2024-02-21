package com.mygdx.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.scene.GameScene;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private ArrayList<Scene> scenes;
    private Scene currentScene;
    private SpriteBatch batch;
    private int sceneNumber; // to determine current scene

    // SceneManager to swap scenes, each scene should do all the world logic

    public SceneManager() {
        scenes = new ArrayList<>();
    }

    public SceneManager (SpriteBatch batch) {
        this.batch = batch;
        scenes = new ArrayList<Scene>();
    }

    public void createGameScene() {
        scenes.add(new GameScene(this, batch));
        //Gdx.app.log("MyGDXGame", "Gamescene created!");
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void setCurrentScene(Scene scene) {
        currentScene = scene;
        currentScene.create();
    }

  public Scene getCurrentScene() {
        return currentScene;
    }

    public void updateCurrentScene() {
        currentScene.update();
    }

    public void renderCurrentScene(SpriteBatch batch) {
        //currentScene.render(batch);
    }

    public void disposeCurrentScene() { //remove current scene
        currentScene.dispose();
    }

    public void swapScene(Scene newScene) { // to change between scenes (to be called by gameMaster)
        disposeCurrentScene();
        setCurrentScene(newScene);
    }
    public Scene getGameScene() {
        if (!scenes.isEmpty()) {
            return scenes.get(0);
        } else {
            return null; // No GameScene found
        }
    }
}
