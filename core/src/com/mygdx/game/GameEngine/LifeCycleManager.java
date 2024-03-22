package com.mygdx.game.GameEngine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.logging.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.mygdx.game.GameEngine.Player.Player;
import com.mygdx.game.GameEngine.Util.SoundManager;

public class LifeCycleManager extends Game {
    private Player player;
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private SoundManager soundManager;

    // Logger creation
    private static final Logger logger = Logger.getLogger(LifeCycleManager.class.getName());

    public LifeCycleManager() {
    
    }

//    public LifeCycleManager(SpaceShooter spaceShooter, Player player, SpriteBatch batch) {
//        this.spaceShooter = spaceShooter;
//        this.player = player;
//        this.batch = batch;
//        sceneManager = new SceneManager(spaceShooter, batch);
//    }

    @Override
    public void create() {
        player = new Player();
        batch = new SpriteBatch();
        sceneManager = new SceneManager(this, batch);
        Gdx.app.log("MyGDXGame", player.getName());
        run();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        player.setScore(1);     // Demo purposes
        sceneManager.dispose();
        System.out.println(player.getName()+" score: " + player.getScore());		// DEMO ONLY
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void render() {
        super.render();
        //lifeCycleManager.run();
//		do {
//			lifeCycleManager.run();
//		}
//		while (player.isPlaying());
    }

    @Override
    public void resize(int width, int height) {
        //gameScene.resize(width, height);
    }

    public void run() {
        try {
            sceneManager.run();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during scene run", e);
        }
    }

//    public void setStartingScreen() {
//        setScreen(getStartingScene());
//    }
//
//    public void setGameScreen() {
//        setScreen(getGameScene());
//    }

    public void playerStop() {
        player.setPlaying(false);
    }

    public void playerStart() {
        player.setPlaying(true);
    }

//    public Scene getStartingScene() {
//        return sceneManager.getStartingScene();
//    }
//    public Scene getGameScene() {
//        return sceneManager.getGameScene();
//    }

}
