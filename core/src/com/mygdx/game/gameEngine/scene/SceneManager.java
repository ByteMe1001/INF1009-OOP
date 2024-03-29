package com.mygdx.game.gameEngine.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.LifeCycleManager;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.player.PlayerManager;
import com.mygdx.game.gameEngine.sound.SoundManager;
import com.mygdx.game.gameLayer.scene.BossScene;
import com.mygdx.game.gameLayer.scene.GameScene;
import com.mygdx.game.gameLayer.scene.QuizScene;
import com.mygdx.game.gameLayer.scene.StartingScene;
import com.mygdx.game.gameLayer.scene.exitScene;

import java.util.ArrayList;

public class SceneManager implements SceneChangeListener{
    private static SceneManager instance;
    private SoundManager soundManager;
    private PlayerManager playerManager;
    private EntityManager entityManager;
    private ArrayList<Scene> scenes;
    private Scene currentScene;
    private SpriteBatch batch;
    private LifeCycleManager lifeCycleManager;
    private boolean isPaused;

    public SceneManager() {
        scenes = new ArrayList<>();
    }

    public SceneManager (LifeCycleManager lifeCycleManager, PlayerManager playerManager, SpriteBatch batch) {
        this.soundManager = SoundManager.getInstance();
        this.playerManager = playerManager;
        this.entityManager = new EntityManager(playerManager, batch);
        this.lifeCycleManager = lifeCycleManager;
        this.batch = batch;
        scenes = new ArrayList<Scene>();
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    // Example code for demo
    public void createStartingScene() {
        scenes.add(new StartingScene(this, entityManager, soundManager, batch));
    }

    public void createGameScene() {
        scenes.add(new GameScene(this, entityManager, soundManager, batch));
    }
    
    public void createQuizScene() {
    	scenes.add(new QuizScene(this, soundManager, entityManager, batch));
    }
    
    public void createBossScene() {
    	scenes.add(new BossScene(this, entityManager, soundManager, batch));
    }
    
    public void createExitScene() {
        scenes.add(new exitScene(this, entityManager, soundManager, batch));
    }

    // Scene Manager functions
    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void setScene(Scene scene) {
        lifeCycleManager.setScreen(scene);
    }

    public void run() {
        StartingScene startingScene = new StartingScene(this, entityManager, soundManager, batch);
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
    
    public Scene getBossScene() {
        for (Scene scene : scenes) {
            if (scene instanceof BossScene) {
                return scene;
            }
        }
        return null; 
    }
    
    public Scene getExitScene() {
        for (Scene scene : scenes) {
            if (scene instanceof exitScene) {
                return scene;
            }
        }
        return null;
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

    public boolean isPaused() {
        return isPaused;
    }

    public void pause(){
        isPaused = true; // Set the isPaused flag to true
        // Pause sound effects and music
        soundManager.pauseAll();
    }

    public void resume(){
        isPaused = false; // Set the isPaused flag to false
        // Resume sound effects and music
        soundManager.resumeAll();
    }
}
