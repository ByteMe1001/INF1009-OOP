package com.mygdx.game;

import com.mygdx.game.entity.Entity;

import java.util.ArrayList;
import java.util.List;

// Name your class file properly
public class AiControlManager {
    private List<Entity> aiEntities; // Stores all AI-controlled entities
    private float screenWidth; // The width of the screen to keep entities within bounds
    private float screenHeight; // The height of the screen for up and down movements
    private float speed; // Speed at which entities move

    // Constants for movement rule set IDs
    public static final int MOVE_LEFT_RIGHT = 1;
    public static final int MOVE_UP_DOWN = 2;

    public AiControlManager(float screenWidth, float screenHeight, float speed) {
        this.aiEntities = new ArrayList<>();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.speed = speed;
    }

    public void addAiEntity(Entity entity) {
        // Initialize each entity's movement parameters as needed
        this.aiEntities.add(entity);
    }

//    public void updateAiEntities() {
//        for (Entity entity : aiEntities) {
//            int movementRulesetId = entity.getMovementRulesetId(); // NEED ENTITY TO HAVE getMovementRulesetId()
//            switch (movementRulesetId) {
//                case MOVE_LEFT_RIGHT:
//                    calculateLeftRightMovement(entity);
//                    break;
//                case MOVE_UP_DOWN:
//                    calculateUpDownMovement(entity);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }


    // The methods are all wrong refer to entity class
//    private void calculateLeftRightMovement(Entity entity) {
//        if (entity.getX() <= 0 || entity.getX() + entity.getWidth() >= screenWidth) {
//            entity.setSpeedX(-entity.getSpeedX());
//        }
//        // This passes back to entity to move itself
//        entity.setMovementDirection(Entity.MovementDirection.HORIZONTAL); //Need to make sure entity has a MovementDirection
//    }
//
//    private void calculateUpDownMovement(Entity entity) {
//        if (entity.getY() <= 0 || entity.getY() + entity.getHeight() >= screenHeight) {
//            entity.setSpeedY(-entity.getSpeedY()); // Reverse direction
//        }
//        // Passes the calculated speed to the entity for it to move itself
//        entity.setMovementDirection(Entity.MovementDirection.VERTICAL);
//    }
}
