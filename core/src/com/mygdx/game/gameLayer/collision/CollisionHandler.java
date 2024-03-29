package com.mygdx.game.gameLayer.collision;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.player.Player;
import com.mygdx.game.gameEngine.player.PlayerManager;
import com.mygdx.game.gameEngine.sound.SoundEffectType;
import com.mygdx.game.gameEngine.sound.SoundManager;
import com.mygdx.game.gameEngine.util.iAiMovement;
import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameLayer.entity.*;

import java.util.ArrayList;

public class CollisionHandler {

    private ArrayList<iCollision> collisionList;
	private PlayerManager playerManager;
    private static SoundManager soundManager;

    private float timeSeconds = 0f;
    private float period = 1.5f;
    
	// Constructor to initialize collisionList
    public CollisionHandler(PlayerManager playerManager, ArrayList<iCollision> collisionList) {
		this.playerManager = playerManager;
        this.collisionList = collisionList;
        soundManager = SoundManager.getInstance();
    }

    public void handleCollision(iCollision x, iCollision y){
    	
    	if (x instanceof Boy && y instanceof Boss || y instanceof Boy && x instanceof Boss) {
    	    Boy boy = (x instanceof Boy) ? (Boy) x : (Boy) y;
    	    Boss boss = (x instanceof Boss) ? (Boss) x : (Boss) y;
    	    
    	    timeSeconds += Gdx.graphics.getDeltaTime();
    	    if (timeSeconds > period) {
    	        timeSeconds -= period;
    	        boy.takeDamage(10);
    	        System.out.println("Player Health: " + boy.getHealth());
    	        System.out.println("Boss Health: " + boss.getHealth());
    	        System.out.println(boy + " has collided with " + boss);
    	    }
    	}

    	else if (x instanceof Boy && y instanceof Enemy || y instanceof Boy && x instanceof Enemy) {
    	    Boy boy = (x instanceof Boy) ? (Boy) x : (Boy) y;
    	    Enemy enemy = (x instanceof Enemy) ? (Enemy) x : (Enemy) y;
    	    
    	    timeSeconds += Gdx.graphics.getDeltaTime();
    	    if (timeSeconds > period) {
    	        timeSeconds -= period;
    	        boy.takeDamage(10);
    	        System.out.println("Player Health: " + boy.getHealth());
    	        System.out.println("Enemy Health: " + enemy.getHealth());
    	        System.out.println(boy + " has collided with " + enemy);
    	    }
    	}

    	else if (x instanceof Bullet && y instanceof Boss || x instanceof Boss && y instanceof Bullet) {
    	    Boss boss = (x instanceof Boss) ? (Boss) x : (Boss) y;
    	    Bullet bullet = (x instanceof Bullet) ? (Bullet) x : (Bullet) y;
    	    
    	    boss.takeDamage(10);
    	    bullet.takeDamage(10);
    	    collisionList.remove(bullet);
    	    bullet.setAlive(false);
    	    soundManager.playSE(SoundEffectType.HIT);
    	    
    	    System.out.println("Boss Health: " + boss.getHealth());
    	    if (boss.getHealth() <= 0) {
    	        collisionList.remove(boss);
    	        boss.setAlive(false);
				int score = playerManager.getScore();
				score += 500;
				playerManager.setScore(score);
				System.out.print("Score: " + playerManager.getScore());
    	    }
    	    
    	    if (bullet.getY() >= 500) {
    	        collisionList.remove(bullet);
    	        bullet.setAlive(false);
    	    }
    	}

    	else if (x instanceof Bullet && y instanceof Enemy || x instanceof Enemy && y instanceof Bullet) {
    	    Enemy enemy = (x instanceof Enemy) ? (Enemy) x : (Enemy) y;
    	    Bullet bullet = (x instanceof Bullet) ? (Bullet) x : (Bullet) y;
    	    
    	    enemy.takeDamage(10);
    	    collisionList.remove(bullet);
    	    bullet.setAlive(false);
    	    soundManager.playSE(SoundEffectType.HIT);
    	    System.out.println("Enemy Health: " + enemy.getHealth());
    	    
    	    if (enemy.getHealth() <= 0) {
				playerManager.createPlayer("Player");
				int score = playerManager.getScore();
				score += 10;
				playerManager.setScore(score);
				System.out.print("Score: " + playerManager.getScore());
				enemy.setAlive(false);
				collisionList.remove(enemy);
    	    }
    	    
    	    if (bullet.getY() >= 500) {
    	        collisionList.remove(bullet);
    	        bullet.setAlive(false);
    	    }
    	}

    	else if (x instanceof Boy && y instanceof EnemyBullet || x instanceof EnemyBullet && y instanceof Boy) {
    	    Boy boy = (x instanceof Boy) ? (Boy) x : (Boy) y;
    	    EnemyBullet enemyBullet = (x instanceof EnemyBullet) ? (EnemyBullet) x : (EnemyBullet) y;
    	    
    	    boy.takeDamage(10);
    	    collisionList.remove(enemyBullet);
    	    enemyBullet.setAlive(false);
			soundManager.playSE(SoundEffectType.COLLECT);
    	    System.out.println("Player Health: " + boy.getHealth());
    	    
    	    if (boy.getHealth() <= 0) {
    	        collisionList.remove(boy);
    	        boy.setAlive(false);
    	    }
    	    
    	    if (enemyBullet.getY() == 0) {
    	        enemyBullet.setAlive(false);
    	        collisionList.remove(enemyBullet);
    	    }
    	}

    	else if (x instanceof Boy && y instanceof HealthPack || x instanceof HealthPack && y instanceof Boy) {
    	    Boy boy = (x instanceof Boy) ? (Boy) x : (Boy) y;
    	    HealthPack healthPack = (x instanceof HealthPack) ? (HealthPack) x : (HealthPack) y;
    	    
    	    int powerupLevel = boy.getPowerUpLevel();
    	    collisionList.remove(healthPack);
    	    soundManager.playSE(SoundEffectType.COLLECT);
    	    healthPack.setAlive(false);
    	    powerupLevel += 1;
    	    boy.setPowerUpLevel(powerupLevel);
    	    System.out.println("Power Up Level: " + boy.getPowerUpLevel());
    	    
    	    if (boy.getPowerUpLevel() == 1) {
    	        boy.heal(30);
    	        boy.setSpeed(boy.getSpeed() + 50);
    	    } else if (boy.getPowerUpLevel() == 2) {
    	        boy.heal(40);
    	        boy.setSpeed(boy.getSpeed() + 50);
    	    } else if (boy.getPowerUpLevel() == 3) {
    	        boy.heal(50);
    	        boy.setSpeed(boy.getSpeed() + 50);
    	    } else if (boy.getPowerUpLevel() >= 4){
				System.out.println("Hit power up limit");
			}
    	    
    	    System.out.println("Player speed increased to: " + boy.getSpeed());
    	    System.out.println("Player Healed to HP: " + boy.getHealth());
    	    
    	    if (healthPack.getY() == 0) {
    	        healthPack.setAlive(false);
    	        collisionList.remove(healthPack);
    	    }
    	}

    }
}
