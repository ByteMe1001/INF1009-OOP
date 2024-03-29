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

    private ArrayList<PlayableEntity> playerEntityList;
    private EntityManager entityManager;

    public AbstractGamePlayerManager() {

    }

    public AbstractGamePlayerManager(EntityManager entityManager, ArrayList<PlayableEntity> playerEntityList) {
        this.entityManager = entityManager;
        this.playerEntityList = playerEntityList;
    }

    public abstract void update(EntityManager entityManager);
    public abstract String movement(PlayableEntity entity);

    //~~~~~~~~~~~~~~~~~~~~~~~~~~GETTERS AND SETTERS~~~~~~~~~~~~~~~~~~~~~~~~~~

    public ArrayList<PlayableEntity> getPlayerEntityList() {
        return playerEntityList;
    }

    public void setPlayerEntityList(ArrayList<PlayableEntity> playerEntityList) {
        this.playerEntityList = playerEntityList;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

