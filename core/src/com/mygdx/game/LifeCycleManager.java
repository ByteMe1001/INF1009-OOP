package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.scene.GameScreen;
import com.mygdx.game.scene.Scene;
import com.mygdx.game.scene.SceneManager;

public class LifeCycleManager {
    private Player player;
    private GameScreen gameScreen;
    private SceneManager sceneManager;
    private SpriteBatch batch;

    public LifeCycleManager() {
    
    }

    public void startGame() {
        //sceneManager.setScreen(startScreen);
    }

    public void pause() {

    }

    public void endGame() {

    }


    public LifeCycleManager(MyGdxGame myGdxGame, Player player, SpriteBatch batch) {
        this.player = player;
        this.batch = batch;
        sceneManager = new SceneManager(batch);

//        sceneManager.startGame();
//        sceneManager.endGame();
//        if (gameScreen == null){
//            throw new IllegalStateException("Unable to create screen");
//        }
        //myGdxGame.setScreen(gameScreen);


    }

    public void run() {
        sceneManager.createGameScene();

        // can also use array
//        sceneManager.startGame();
//        sceneManager.gameScreen();
//        sceneManager.endGame();
//        player.setScore(1);
//        playerStop(); // to stop the game
    }

    public void playerStop() {
        player.setPlaying(false);
    }

    public void playerStart() {
        player.setPlaying(true);
    }

    public void dispose() {
        //sceneManager.dispose();
    }
    public Scene getGameScene() {
        return sceneManager.getGameScene();
    }
}
