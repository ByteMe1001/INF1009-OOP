package com.mygdx.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.LifeCycleManager;
import com.mygdx.game.SpaceShooter;
import com.mygdx.game.scene.GameScene;
import com.mygdx.game.util.SoundManager;
import com.mygdx.game.util.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class SceneManager implements SceneChangeListener{
    private SoundManager soundManager;
    private PlayerManager PlayerManager;
    private ArrayList<Scene> scenes;
    private Scene currentScene;
    private SpriteBatch batch;
    private LifeCycleManager lifeCycleManager;

    // SceneManager to swap scenes, each scene should do all the world logic

    public SceneManager() {
        scenes = new ArrayList<>();
    }

    public SceneManager (LifeCycleManager lifeCycleManager, SpriteBatch batch) {
        this.soundManager = new SoundManager();
        this.lifeCycleManager = lifeCycleManager;
        PlayerManager playerManager = new PlayerManager();      // Later change to after starting scene ends
        this.batch = batch;
        scenes = new ArrayList<Scene>();
    }

    // Example code for demo
    public void createStartingScene() {
        scenes.add(new StartingScene(this, soundManager, batch));
    }

    public void createGameScene() {
        scenes.add(new GameScene(this, soundManager, batch));
    }

    // Scene Manager functions
    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void setScene(Scene scene) {
        lifeCycleManager.setScreen(scene);
    }

    public void run() {
        StartingScene startingScene = new StartingScene(this, soundManager, batch);
        if (startingScene == null) {
            throw new IllegalStateException("StartingScene is null after creation");
        } else {
            setCurrentScene(startingScene);
        }
    }

    public void setCurrentScene(Scene scene) {
        if (scene == null) {
            throw new IllegalStateException("Scene is null");
        } else {
            currentScene = scene;
            setScene(scene);
        }
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void swapScene(Scene newScene) { // to change between scenes (to be called by gameMaster)
        if (newScene == null) {
            throw new IllegalStateException("New Scene is null");
        } else {
            disposeCurrentScene(); // Dispose of the current scene
            setCurrentScene(newScene); // Set the new scene as the current scene
        }
    }

    public void renderCurrentScene() {
        currentScene.render(Gdx.graphics.getDeltaTime());
    }

    public void disposeCurrentScene() { //remove current scene

        currentScene.dispose();
    }

    // Hard code for demo
    public Scene getStartingScene() {
        if (!scenes.isEmpty()) {
            return scenes.get(0);
        } else {
            return null; // No GameScene found
        }
    }

    public Scene getGameScene() {
        if (!scenes.isEmpty()) {
            return scenes.get(1);
        } else {
            return null; // No GameScene found
        }
    }

    // Clear all scene assets
    public void dispose() {
        for (Scene scene: scenes) {
            scene.dispose();
        }
        scenes.clear();
        soundManager.dispose();
    }
    @Override
    public void onSceneChange(Scene newScene) {
        // Swap the scene
        swapScene(newScene);
    }
}
