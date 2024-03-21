package com.mygdx.game.ai;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entity.Droplet;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.util.iAiMovement;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Name your class file properly
public class AiControlManager implements iAiMovement {
    private ArrayList<Entity> aiEntityList; // Stores all AI-controlled entities
    private EntityManager entityManager;
    private float screenWidth; // The width of the screen to keep entities within bounds
    private float screenHeight; // The height of the screen for up and down movements
    private float speed; // Speed at which entities move

    // Constants for movement rule set IDs
    public static final int MOVE_UP_DOWN = 1;
    public static final int MOVE_LEFT_RIGHT = 2;
    public static final int MOVE_ALL = 3;

    public AiControlManager(float screenWidth, float screenHeight, EntityManager entityManager, ArrayList<Entity> aiEntityList) {
        this.aiEntityList = aiEntityList;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.entityManager = entityManager;
    }

    public void movement(Entity entity, float x, float y, float width, float height, int movementPreference) {
    	if (entity instanceof Droplet) {
            Droplet droplet = (Droplet) entity;
            switch (movementPreference) {
            case MOVE_UP_DOWN:
                if ("UP".equals(droplet.getCurrentDirection())) {
                    droplet.up(entityManager, entity, y, height);
                } else {
                    droplet.down(entityManager, entity, y);
                }
                break;
            case MOVE_LEFT_RIGHT:
                if ("LEFT".equals(droplet.getCurrentDirection())) {
                    droplet.left(entityManager, entity, x);
                } else {
                    droplet.right(entityManager, entity, x, width);
                }
                break;
    		}
    	}
    }
}
