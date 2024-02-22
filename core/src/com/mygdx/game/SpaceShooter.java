package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.util.SoundManager;

public class SpaceShooter extends Game {

	private Player player;
	private LifeCycleManager lifeCycleManager;
	private SpriteBatch batch;
	private SoundManager soundManager;

	@Override
	public void create() {
		player = new Player();
		batch = new SpriteBatch();
		lifeCycleManager = new LifeCycleManager(this, player, batch);
		Gdx.app.log("MyGDXGame", player.getName()); //testing line
		//setScreen(lifeCycleManager.getGameScene());
//		this.soundManager = new SoundManager();d
//		soundManager.playMusic(1);
		lifeCycleManager.run();
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
}
