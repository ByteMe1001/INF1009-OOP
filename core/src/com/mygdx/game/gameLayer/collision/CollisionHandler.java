package com.mygdx.game.gameLayer.collision;

import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameLayer.entity.Boss;
import com.mygdx.game.gameLayer.entity.Boy;
import com.mygdx.game.gameLayer.entity.Bullet;
import com.mygdx.game.gameLayer.entity.EntityType;

import java.util.ArrayList;

public class CollisionHandler {

    private ArrayList<iCollision> collisionList;
    public void handleCollision(iCollision x, iCollision y){
        if(x.getClass().equals(Boy.class) && y.getClass().equals(Boss.class) || y.getClass().equals(Boy.class) && x.getClass().equals(Boss.class)){
            ((Boy) x).takeDamage(10);
            ((Boss) y).takeDamage(10);
            System.out.println(x + "has collided with " + y);
            if(((Boss) y).getHealth() == 0){
                collisionList.remove(y);

            }
        }


    }



}
