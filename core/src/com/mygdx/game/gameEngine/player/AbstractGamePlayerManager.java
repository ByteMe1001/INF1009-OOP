package com.mygdx.game.gameEngine.player;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.entity.PlayableEntity;
import com.mygdx.game.gameEngine.util.iIO;
import com.mygdx.game.gameEngine.util.iPlayerMovement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractGamePlayerManager implements iIO {

    // List to hold player entities
    private ArrayList<PlayableEntity> playerEntityList;

    // Constructor
    public AbstractGamePlayerManager() {
        // Do nothing
    }

    // Constructor with playerEntityList
    public AbstractGamePlayerManager(ArrayList<PlayableEntity> playerEntityList) {
        this.playerEntityList = playerEntityList;
    }

    // Abstract methods
    public abstract void update(EntityManager entityManager);
    public abstract String movement(PlayableEntity entity);

    //~~~~~~~~~~~~~~~~~~~~~~~~~~GETTERS AND SETTERS~~~~~~~~~~~~~~~~~~~~~~~~~~

    public ArrayList<PlayableEntity> getPlayerEntityList() {
        return playerEntityList;
    }

    public void setPlayerEntityList(ArrayList<PlayableEntity> playerEntityList) {
        this.playerEntityList = playerEntityList;
    }
}

