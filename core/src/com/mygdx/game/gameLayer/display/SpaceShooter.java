package com.mygdx.game.gameLayer.display;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.LifeCycleManager;
import com.mygdx.game.gameEngine.player.Player;
import com.mygdx.game.gameEngine.sound.SoundManager;

public class SpaceShooter extends Game {

	private Player player;
	private LifeCycleManager lifeCycleManager;
	private SpriteBatch batch;
	private SoundManager soundManager;

	@Override
	public void create() {
		player = new Player();
		batch = new SpriteBatch();
		//lifeCycleManager = new LifeCycleManager(this, player, batch);
		Gdx.app.log("MyGDXGame", player.getName());
		lifeCycleManager.run();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		lifeCycleManager.dispose();
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
}
