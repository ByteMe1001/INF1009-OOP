package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class AiManager {
    private List<Entity> aiEntities; // Stores all AI-controlled entities
    private iAiMovement defaultMovementBehavior;

    //SAVE THIS FOR ACTUAL IMPLEMENTATION NEXT TIME 
     public AiManager(float maxMoveDistance) {
        this.aiEntities = new ArrayList<>();
        this.defaultMovementBehavior = new AiMovement(maxMoveDistance);
    } 
    
     	/* public AiManager() {
        this.aiEntities = new ArrayList<>();
        this.defaultMovementBehavior = new AiMovement();
    } */

    // Method to add an AI-controlled entity
    public void addAiEntity(Entity entity) {
        this.aiEntities.add(entity);
    }

    public void updateAiEntities() {
        for (Entity entity : aiEntities) {
            defaultMovementBehavior.move(entity);
        }
    }

}
