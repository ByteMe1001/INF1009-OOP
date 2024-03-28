package com.mygdx.game.gameLayer.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameLayer.entity.*;

import java.util.ArrayList;

public class CollisionHandler {

    private ArrayList<iCollision> collisionList;
    private float timeSeconds = 0f;
    private float period = 3f;
    
	// Constructor to initialize collisionList
    public CollisionHandler(ArrayList<iCollision> collisionList) {
        this.collisionList = collisionList;
    }

    public void handleCollision(iCollision x, iCollision y){

        if(x.getClass().equals(Boy.class) && y.getClass().equals(Boss.class)
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


        else if (x.getClass().equals(Bullet.class) && y.getClass().equals(Boss.class) || x.getClass().equals(Boss.class) && y.getClass().equals(Bullet.class)){
            ((Boss) x).takeDamage(10);
            ((Bullet) y).takeDamage(10);
            collisionList.remove(y);
            ((Bullet) y).setAlive(false);

            System.out.println("Boss Health" + x + " " + ((Boss) x).getHealth());
             if(((Boss) x).getHealth() == 0) {
                 collisionList.remove(x);
                 ((Boss) x).setAlive(false);

             }

        }

        else if (x.getClass().equals(Bullet.class) && y.getClass().equals(Enemy.class) || x.getClass().equals(Enemy.class) && y.getClass().equals(Bullet.class)){
            ((Enemy) x).takeDamage(10);
            collisionList.remove(y);
            ((Bullet) y).setAlive(false);

            System.out.println("Boss Health" + x + " " + ((Enemy) x).getHealth());
            if(((Enemy) x).getHealth() == 0) {
                collisionList.remove(x);
                ((Enemy) x).setAlive(false);

            }

        }

        else if (x.getClass().equals(Boy.class) && y.getClass().equals(EnemyBullet.class)||x.getClass().equals(EnemyBullet.class) && y.getClass().equals(Boy.class)){
            ((CollidableEntities) x).takeDamage(10);
            collisionList.remove(y);
            ((CollidableEntities) y).setAlive(false);
            System.out.println("Player Health: " + ((CollidableEntities) x).getHealth());
            if(((CollidableEntities) x).getHealth() == 0) {
                collisionList.remove(x);
                ((CollidableEntities) x).setAlive(false);
            }
            if(((EnemyBullet) y).getY() == 0){
                ((EnemyBullet) y).setAlive(false);
                collisionList.remove(y);
            }
        }

        else if (x.getClass().equals(Boy.class) && y.getClass().equals(HealthPack.class)){
            ((Boy) x).heal(30);
            System.out.println("Player Healed to HP: " + ((Boy) x).getHealth());
            collisionList.remove(y);
            ((HealthPack) y).setAlive(false);
            if(((HealthPack) y).getY() == 0){
                ((HealthPack) y).setAlive(false);
                collisionList.remove(y);
            }

        }


    }



}
