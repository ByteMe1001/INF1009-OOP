package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.scene.GameScreen;

public class MyGdxGame extends Game {

	Player player;
	LifeCycleManager lifeCycleManager;
	SpriteBatch batch;


	@Override
	public void create() {
		player = new Player();
		batch = new SpriteBatch();
		lifeCycleManager = new LifeCycleManager(this, player);
		Gdx.app.log("MyGDXGame", player.getName()); //testing line

		//gameScreen = new GameScreen();
		//setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		//gameScreen.dispose();
		batch.dispose();
		lifeCycleManager.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void render() {
		super.render();
		do {
			lifeCycleManager.run();
		}
		while (player.isPlaying());
	}

	@Override
	public void resize(int width, int height) {
		//gameScreen.resize(width, height);
	}
}
