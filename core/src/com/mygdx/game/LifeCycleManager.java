package com.mygdx.game;

import com.mygdx.game.scene.GameScreen;
import com.mygdx.game.scene.SceneManager;

public class LifeCycleManager {
    private Player player;
    private GameScreen gameScreen;
    private SceneManager sceneManager;

    public LifeCycleManager() {
    
    }

    public void startGame() {
        //sceneManager.setScreen(startScreen);
    }

    public void pause() {

    }

    public void endGame() {

    }


    public LifeCycleManager(MyGdxGame myGdxGame, Player player) {
        this.player = player;
        //gameScreen = new GameScreen();
        sceneManager = new SceneManager();
//        sceneManager.startGame();
//        sceneManager.endGame();
//        if (gameScreen == null){
//            throw new IllegalStateException("Unable to create screen");
//        }
        //myGdxGame.setScreen(gameScreen);


    }
}
