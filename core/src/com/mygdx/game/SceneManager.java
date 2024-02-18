// keeps track of the scenes in a game, allowing to switch between them
// to be further edited :(

package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private List<Scene> scenes;
    private Scene currentScene;

    // Default constructor
    public SceneManager() {
        scenes = new ArrayList<>();
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void setStartingScene(Scene startingScene) {
        currentScene = startingScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene scene) {
        currentScene = scene;
    }

    public void dispose() {
        for (Scene scene : scenes) {
            scene.dispose();
        }
    }
}

