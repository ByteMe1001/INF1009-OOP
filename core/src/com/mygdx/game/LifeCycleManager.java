package com.mygdx.game;

public class LifeCycleManager {
    private Player player;
    private GameScreen gameScreen;
    private SceneManager sceneManager;

    public LifeCycleManager() {
    
    }

    public void startGame() {
        screenmanager.setScreen(startScreen);
    }


    public LifeCycleManager(MyGdxGame myGdxGame, Player player) {
        this.player = player;
        gameScreen = new GameScreen();
        if (gameScreen == null){
            throw new IllegalStateException("Unable to create screen");
        }
        myGdxGame.setScreen(gameScreen);
    }
}
