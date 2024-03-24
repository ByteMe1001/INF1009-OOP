package com.mygdx.game.gameLayer.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameLayer.entity.Boss;
import com.mygdx.game.gameLayer.entity.Boy;
import com.mygdx.game.gameLayer.entity.Bullet;
import com.mygdx.game.gameLayer.entity.EntityType;

import java.util.ArrayList;

public class CollisionHandler {

    private ArrayList<iCollision> collisionList;
    private float timeSeconds = 0f;
    private float period = 3f;

    public void handleCollision(iCollision x, iCollision y){
        if(x.getClass().equals(Boy.class) && y.getClass().equals(Boss.class)
            || y.getClass().equals(Boy.class) && x.getClass().equals(Boss.class)){
            // Handle collision
            ((Boy) x).takeDamage(10);
            ((Boss) y).takeDamage(10);
            timeSeconds += Gdx.graphics.getDeltaTime();
            if(timeSeconds > period){
                timeSeconds -= period;
                //System.out.println("Cooldown on Collision");
                System.out.println(x + "has collided with " + y);
            }

            if(((Boss) y).getHealth() == 0){
                collisionList.remove(y);
            }
        }

        //Testing purposes
        else if (x.getClass().equals(Bullet.class) && y.getClass().equals(Boss.class) || y.getClass().equals(Bullet.class) && x.getClass().equals(Boss.class)){

            //System.out.println(x + "has collided with " + y);
        }
        else if (x.getClass().equals(Boss.class) && y.getClass().equals(Boss.class)){
            timeSeconds += Gdx.graphics.getRawDeltaTime();
            if(timeSeconds > period){
                timeSeconds -= period;
                //System.out.println("Cooldown on Collision");
            }
        }


    }



}
