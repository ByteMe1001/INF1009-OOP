package com.mygdx.game.gameEngine.collision;

import com.mygdx.game.gameEngine.entity.Entity;

import java.util.List;
import java.util.ArrayList;

import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.player.PlayerManager;
import com.mygdx.game.gameEngine.sound.SoundManager;
import com.mygdx.game.gameEngine.util.iCollision;
import com.mygdx.game.gameLayer.collision.CollisionHandler;


public class CollisionManager {

    private ArrayList<iCollision> collisionList;
    private PlayerManager playerManager;
    private CollisionHandler collisionHandler;

    // Default constructor
    public CollisionManager() {
        // Do nothing
    }

    public CollisionManager(PlayerManager playerManager, ArrayList<iCollision> collisionList) {
        this.playerManager = playerManager;
        this.collisionList = collisionList;
        this.collisionHandler = new CollisionHandler(playerManager, collisionList);
    }


    // Detect collision through n! loop within collidable list to find boundingBox overlap
    public void detectCollision(EntityManager entityManager){
        for (int i = 0; i < collisionList.size() - 1; i++){
            for (int j = i + 1; j < collisionList.size(); j++){
                if(collisionList.get(i).collidesWith(collisionList.get(j))){ //If entities overlaps with one another, print Collision Detected in console
                    collisionHandler.handleCollision(collisionList.get(i), collisionList.get(j));
                }
            }
        }
    }
}
