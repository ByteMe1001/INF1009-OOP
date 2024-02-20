package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.scene.GameScreen;

public class MyGdxGame extends Game {

	GameScreen gameScreen;
	Player player;
	LifeCycleManager lifeCycleManager;

	@Override
	public void create() {
		player = new Player();
		lifeCycleManager = new LifeCycleManager(this, player);

		//gameScreen = new GameScreen();
		//setScreen(gameScreen);
		Gdx.app.log("MyGDXGame", player.getName()); //testing line
	}

	@Override
	public void dispose() {
		super.dispose();
		//gameScreen.dispose();
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
		//gameScreen.resize(width, height);
	}
}
