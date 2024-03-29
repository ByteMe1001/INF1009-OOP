package com.mygdx.game.gameEngine.ai;

import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.util.iAiMovement;


import java.util.ArrayList;
import java.util.Random;

/**
 * AIControlManager is responsible for managing the AI-controlled entities in the game.
 * It updates the movement of each AI-controlled entity and handles their movement strategy.
 */
public class AIControlManager {
    private ArrayList<iAiMovement> aiEntityList; // Stores all AI-controlled entities
    private float speed; // Speed at which entities move
    private Random random;


    public AIControlManager( ArrayList<iAiMovement> aiEntityList) {
        this.aiEntityList = aiEntityList;
        this.random = new Random();
    }

    // Updates the movement of each AI-controlled entity.
    public void update() {
        for (iAiMovement e : aiEntityList) {
            e.movement();
            movement(e);
        }
    }


    /**
     * Handles the movement of a given AI-controlled entity.
     * If the entity's change rate is negative, it is reset to a random positive value.
     * Then, the entity's movement strategy is used to move the entity.
     */

    public void movement(iAiMovement entity) {
        // If change rate is negative reset it back to random +ve
        if (entity.getChangeRate() < 0 ) {
            entity.setChangeRate(random.nextInt(entity.getDefaultChangeRate() + 1));
        }
        entity.getMovementStrategy().move(entity);
    }
}
