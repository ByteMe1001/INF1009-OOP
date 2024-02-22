package com.mygdx.game.ai;

import com.badlogic.gdx.Gdx;
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
    private ArrayList<Entity> entityList;
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
        Random random = new Random();
        //this.speed = speed;
    }

    public void movement(Entity entity, float x, float y, float width, float height, int defaultChangeRate) {
            //System.out.println(entityManager.getChangeRate(entity));
            if (entityManager.getChangeRate(entity) >= 0 ) {
                switch (entityManager.getDirection(entity)) {
                    case 1:
                        setUpDown(entityManager, entity, y, height);
                        break;
                    case 2:
                        setLeftRight(entityManager, entity, x, width);
                        break;
                    case 3:
                        setAll(entityManager, entity, x, y, width, height);
                        break;
                }
            }
            else entityManager.setChangeRate(entity, defaultChangeRate);
        }
    }
