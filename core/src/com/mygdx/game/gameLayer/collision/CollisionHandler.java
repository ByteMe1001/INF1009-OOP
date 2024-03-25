package com.mygdx.game.gameLayer.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameLayer.entity.*;

import java.util.ArrayList;

public class CollisionHandler {

    private ArrayList<iCollision> collisionList;
    private float timeSeconds = 0f;
    private float period = 3f;

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
                System.out.println("Player Health: " + ((CollidableEntities) x).getHealth());
                System.out.println("Boss Health: " + ((CollidableEntities) y).getHealth());
                System.out.println(x + "has collided with " + y);
            }

            if(((Boy) x).getHealth() == 0){
                //collisionList.remove(y);
            }
        }

        //Testing purposes
        else if (x.getClass().equals(Boy.class) && y.getClass().equals(EnemyBullet.class)){
            ((Boy) x).heal(10);
            System.out.println("Player has been healed back to HP: " + ((Boy) x).getHealth() );

            //System.out.println(x + "has collided with " + y);
        }
        else if (x.getClass().equals(Boss.class) && y.getClass().equals(Boss.class)){
            timeSeconds += Gdx.graphics.getDeltaTime();
            if(timeSeconds > period){
                timeSeconds -= period;
                //System.out.println("Cooldown on Collision");
            }
        }


    }



}
