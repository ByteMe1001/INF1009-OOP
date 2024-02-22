package com.mygdx.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceShooter;
import com.mygdx.game.scene.GameScene;

import java.util.ArrayList;
import java.util.List;

public class SceneManager implements SceneChangeListener{
    private ArrayList<Scene> scenes;
    private Scene currentScene;
    private SpriteBatch batch;
    private SpaceShooter spaceShooter;
    private int sceneNumber; // to determine current scene

    // SceneManager to swap scenes, each scene should do all the world logic

    public SceneManager() {
        scenes = new ArrayList<>();
    }

    public SceneManager (SpaceShooter spaceShooter, SpriteBatch batch) {
        this.spaceShooter = spaceShooter;
        this.batch = batch;
        scenes = new ArrayList<Scene>();
    }

    public void createStartingScene() {
        scenes.add(new StartingScene(this, batch));
    }

    public void createGameScene() {
        scenes.add(new GameScene(this, batch));
        //Gdx.app.log("MyGDXGame", "Gamescene created!");
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void setScene(Scene scene) {
        spaceShooter.setScreen(scene);
    }

    public void run() {
        StartingScene startingScene = new StartingScene(this, batch);
        setCurrentScene(startingScene);
    }

    public void setCurrentScene(Scene scene) {
        currentScene = scene;
        setScene(scene);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

//    public void updateCurrentScene() {
//        currentScene.update();
//    }

    public void swapScene(Scene newScene) { // to change between scenes (to be called by gameMaster)
        disposeCurrentScene(); // Dispose of the current scene
        setCurrentScene(newScene); // Set the new scene as the current scene
    }

    public void renderCurrentScene(SpriteBatch batch) {
        //currentScene.render(batch);
    }

    public void disposeCurrentScene() { //remove current scene
        currentScene.dispose();
    }

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

    @Override
    public void onSceneChange(Scene newScene) {
        // Swap the scene
        swapScene(newScene);
    }
}
