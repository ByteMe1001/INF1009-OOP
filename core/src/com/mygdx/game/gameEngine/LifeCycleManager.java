package com.mygdx.game.gameEngine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.logging.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.player.Player;
import com.mygdx.game.gameEngine.player.PlayerManager;
import com.mygdx.game.gameEngine.scene.SceneManager;
import com.mygdx.game.gameEngine.sound.SoundManager;

public class LifeCycleManager extends Game {
    private Player player;
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private SoundManager soundManager;
    private PlayerManager playerManager;

    // Logger creation
    private static final Logger logger = Logger.getLogger(LifeCycleManager.class.getName());

    public LifeCycleManager() {
    
    }

    @Override
    public void create() {
        player = new Player();
        playerManager = new PlayerManager();
        playerManager.addPlayer(player);
        batch = new SpriteBatch();
        sceneManager = new SceneManager(this, playerManager, batch);
        Gdx.app.log("MyGDXGame", player.getName());
        run();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        sceneManager.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void render() {
        super.render();
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

    public void playerStop() {
        player.setPlaying(false);
    }

    public void playerStart() {
        player.setPlaying(true);
    }


}
