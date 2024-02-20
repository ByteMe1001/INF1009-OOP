package com.mygdx.game.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private List<Scene> scenes;
    private Scene currentScene;

    // SceneManager to swap scenes, each scene should do all the world logic

    public SceneManager() {
        scenes = new ArrayList<>();
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
        currentScene.render(batch);
    }

    public void disposeCurrentScene() { //remove current scene
        currentScene.dispose();
    }

    public void swapScene(Scene newScene) { // to change between scenes (to be called by gameMaster)
        disposeCurrentScene();
        setCurrentScene(newScene);
    }

}
