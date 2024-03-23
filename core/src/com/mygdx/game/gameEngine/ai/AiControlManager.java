package com.mygdx.game.gameEngine.ai;

import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.util.iAiMovement;


import java.util.ArrayList;
import java.util.Random;

public class AiControlManager{
    private ArrayList<iAiMovement> aiEntityList; // Stores all AI-controlled entities
    private EntityManager entityManager;
    private float screenWidth; // The width of the screen to keep entities within bounds
    private float screenHeight; // The height of the screen for up and down movements
    private float speed; // Speed at which entities move
    private Random random;

    // Constants for movement rule set IDs
//    public static final int MOVE_UP_DOWN = 1;
//    public static final int MOVE_LEFT_RIGHT = 2;
//    public static final int MOVE_ALL = 3;

    public AiControlManager(float screenWidth, float screenHeight, EntityManager entityManager, ArrayList<iAiMovement> aiEntityList) {
        this.aiEntityList = aiEntityList;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.entityManager = entityManager;
        this.random = new Random();
    }

    public void update() {
        for (iAiMovement e : aiEntityList) {
            e.movement();
            movement(e);
        }
    }

    // Decides which movement style to use based on entity movementSetID
    public void movement(iAiMovement entity) {
        // If change rate is negative reset it back to random +ve
        if (entity.getChangeRate() < 0 ) {
            entity.setChangeRate(random.nextInt(entity.getDefaultChangeRate() + 1));
        }
        entity.getMovementStrategy().move(entity);
    }

}
