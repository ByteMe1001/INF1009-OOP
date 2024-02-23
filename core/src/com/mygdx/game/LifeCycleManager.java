package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.scene.Scene;
import com.mygdx.game.scene.SceneManager;
import java.util.logging.*;

public class LifeCycleManager {
    private Player player;
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private SpaceShooter spaceShooter;

    // Logger creation
    private static final Logger logger = Logger.getLogger(LifeCycleManager.class.getName());

    public LifeCycleManager() {
    
    }

    public LifeCycleManager(SpaceShooter spaceShooter, Player player, SpriteBatch batch) {
        this.spaceShooter = spaceShooter;
        this.player = player;
        this.batch = batch;
        sceneManager = new SceneManager(spaceShooter, batch);
    }


    public void run() {
        try {
            sceneManager.run();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during scene run", e);
        }
    }

    public void setStartingScreen(SpaceShooter spaceShooter) {
        spaceShooter.setScreen(getStartingScene());
    }

    public void setGameScreen(SpaceShooter spaceShooter) {
        spaceShooter.setScreen(getGameScene());
    }

    public void playerStop() {
        player.setPlaying(false);
    }

    public void playerStart() {
        player.setPlaying(true);
    }

    public void dispose() {
        player.setScore(1);     // Demo purposes
        sceneManager.dispose();
    }
    public Scene getStartingScene() {
        return sceneManager.getStartingScene();
    }
    public Scene getGameScene() {
        return sceneManager.getGameScene();
    }

}
