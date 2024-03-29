package com.mygdx.game.gameLayer.collision;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.player.Player;
import com.mygdx.game.gameEngine.player.PlayerManager;
import com.mygdx.game.gameEngine.sound.SoundManager;
import com.mygdx.game.gameEngine.util.iAiMovement;
import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameLayer.entity.*;

import java.util.ArrayList;

public class CollisionHandler {

    private ArrayList<iCollision> collisionList;
	private PlayerManager playerManager;
    private static SoundManager soundManager;

	private Player player;
    private float timeSeconds = 0f;
    private float period = 1.5f;
    
	// Constructor to initialize collisionList
    public CollisionHandler(PlayerManager playerManager, ArrayList<iCollision> collisionList) {
		this.player = new Player("Player");
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
    	    soundManager.playSE("GameScene_Collision");
    	    
    	    System.out.println("Boss Health: " + boss.getHealth());
    	    if (boss.getHealth() <= 0) {
    	        collisionList.remove(boss);
    	        boss.setAlive(false);
				playerManager.createPlayer("Player");
				playerManager.addPlayer(player);
				int score = playerManager.getScore(player);
				score += 500;
				//player.setScore(score);
				playerManager.setScore(player, score);
				System.out.print("Score: " + playerManager.getScore(player));
    	        //int score = player.getScore() + 500;
    	        //player.setScore(score);
    	        //System.out.println("Player Score: " + player.getScore());
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
    	    soundManager.playSE("GameScene_Collision");
    	    System.out.println("Enemy Health: " + enemy.getHealth());
    	    
    	    if (enemy.getHealth() <= 0) {
				playerManager.createPlayer("Player");
				playerManager.addPlayer(player);
				int score = playerManager.getScore(player);
				score += 10;
				//player.setScore(score);
				playerManager.setScore(player, score);
				System.out.print("Score: " + playerManager.getScore(player));
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
    	    soundManager.playSE("eatPowerup");
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


     /*   if(x.getClass().equals(Boy.class) && y.getClass().equals(Boss.class)
            || y.getClass().equals(Boy.class) && x.getClass().equals(Boss.class)){
            // Handle collision

            timeSeconds += Gdx.graphics.getDeltaTime();
            if(timeSeconds > period){
                timeSeconds -= period;
                ((Boy) x).takeDamage(10);
                //((Boss) y).takeDamage(10);
                //System.out.println("Cooldown on Collision");
                System.out.println("Player Health: " + ((Boy) x).getHealth());
                System.out.println("Boss Health: " + ((Boss) y).getHealth());
                System.out.println(x + "has collided with " + y);
            }

        }

        else if(x.getClass().equals(Boy.class) && y.getClass().equals(Enemy.class)
                || y.getClass().equals(Boy.class) && x.getClass().equals(Enemy.class)){
            // Handle collision

            timeSeconds += Gdx.graphics.getDeltaTime();
            if(timeSeconds > period){
                timeSeconds -= period;
                ((Boy) x).takeDamage(10);
                //((Boss) y).takeDamage(10);
                //System.out.println("Cooldown on Collision");
                System.out.println("Player Health: " + ((Boy) x).getHealth());
                System.out.println("Enemy Health: " + ((Enemy) y).getHealth());
                System.out.println(x + "has collided with " + y);
            }

        }


        else if (x.getClass().equals(Bullet.class) && y.getClass().equals(Boss.class)
                || x.getClass().equals(Boss.class) && y.getClass().equals(Bullet.class)){
            ((Boss) x).takeDamage(10);
            ((Bullet) y).takeDamage(10);
            collisionList.remove(y);
            ((Bullet) y).setAlive(false);
            soundManager.playSE("GameScene_Collision");

            System.out.println("Boss Health" + x + " " + ((Boss) x).getHealth());
             if(((Boss) x).getHealth() == 0) {
                 collisionList.remove(x);
                 ((Boss) x).setAlive(false);

                     int score = player.getScore(); // Retrieve the current score from the player
                     collisionList.remove(x);
                     ((Boss) x).setAlive(false);
                     score += 500; // Increment the score by 10 for killing an enemy
                     player.setScore(score); // Set the updated score back to the player
                     System.out.println("Player Score: " + player.getScore());


             }

             if(((Bullet) y).getY() >= 500){
                 collisionList.remove(y);
                 ((Bullet) y).setAlive(false);
             }

        }

        else if (x.getClass().equals(Bullet.class) && y.getClass().equals(Enemy.class)
                || x.getClass().equals(Enemy.class) && y.getClass().equals(Bullet.class)){
            ((Enemy) x).takeDamage(10);
            collisionList.remove(y);
            ((Bullet) y).setAlive(false);
            soundManager.playSE("GameScene_Collision");
            System.out.println("Boss Health" + x + " " + ((Enemy) x).getHealth());
            if(((Enemy) x).getHealth() == 0) {

                int score = player.getScore(); // Retrieve the current score from the player
                collisionList.remove(x);
                ((Enemy) x).setAlive(false);
                score += 10; // Increment the score by 10 for killing an enemy
                player.setScore(score); // Set the updated score back to the player
                System.out.println("Player Score: " + player.getScore());

            }
            if(((Bullet) y).getY() >= 500){
                collisionList.remove(y);
                ((Bullet) y).setAlive(false);
            }

        }

        else if (x.getClass().equals(Boy.class) && y.getClass().equals(EnemyBullet.class)
                ||x.getClass().equals(EnemyBullet.class) && y.getClass().equals(Boy.class)){
            ((Boy) x).takeDamage(10);
            collisionList.remove(y);
            ((EnemyBullet) y).setAlive(false);
            System.out.println("Player Health: " + ((Boy) x).getHealth());
            if(((Boy) x).getHealth() == 0) {
                collisionList.remove(x);
                ((Boy) x).setAlive(false);
            }
            if(((EnemyBullet) y).getY() == 0){
                ((EnemyBullet) y).setAlive(false);
                collisionList.remove(y);
            }
        }

        else if (x.getClass().equals(Boy.class) && y.getClass().equals(HealthPack.class)){


            int powerupLevel = ((Boy) x).getPowerUpLevel(); // Retrieve the current score from the player
            collisionList.remove(y);
            soundManager.playSE("eatPowerup");
            ((HealthPack) y).setAlive(false);
            powerupLevel += 1; // Increment the score by 10 for killing an enemy
            ((Boy) x).setPowerUpLevel(powerupLevel); // Set the updated score back to the player
            System.out.println("Power Up Level: " + ((Boy) x).getPowerUpLevel());
            if(((Boy) x).getPowerUpLevel() == 1) {
                ((Boy) x).heal(30);
                ((Boy) x).setSpeed(((Boy) x).getSpeed() + 50);

            }
            if(((Boy) x).getPowerUpLevel() == 2) {
                ((Boy) x).heal(40);
                ((Boy) x).setSpeed(((Boy) x).getSpeed() + 50);

            }
            if(((Boy) x).getPowerUpLevel() == 3) {
                ((Boy) x).heal(50);
                ((Boy) x).setSpeed(((Boy) x).getSpeed() + 50);
            }
            System.out.println("Player speed increased to: " + ((Boy) x).getSpeed());
            System.out.println("Player Healed to HP: " + ((Boy) x).getHealth());
            collisionList.remove(y);
            ((HealthPack) y).setAlive(false);
            if(((HealthPack) y).getY() == 0){
                ((HealthPack) y).setAlive(false);
                collisionList.remove(y);
            }
        } */


    }
}
