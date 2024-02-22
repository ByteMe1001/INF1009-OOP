package com.mygdx.game;

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
        //this.speed = speed;
    }

//    public void addAiEntities(ArrayList<Entity> entityList) {
//        for (Entity entity : entityList) {
//
//        }
//
//    }

    public void addAiEntity(Entity entity) {
        // Initialize each entity's movement parameters as needed
        this.aiEntityList.add(entity);
    }

    public void movement(ArrayList<Entity> aiEntityList) {

        Random random = new Random();
        int randomNumber = random.nextInt(2) + 1;

        for (Entity entity : aiEntityList) {
            //System.out.println(entityManager.getChangeRate(entity));
            if (entityManager.getChangeRate(entity) >= 0 ){
                switch(entityManager.getDirection(entity)) {
                    case 1:
                        setUpDown(entityManager, entity);
                        break;
                    case 2:
                        setLeftRight(entityManager, entity);
                        break;
                    case 3:
                        System.out.println("case 3");
                        setAll(entityManager, entity);
                        break;
                }
            }
            else entityManager.setChangeRate(entity, entityManager.getDefaultChangeRate(entity));
        }

    }





    
    

//    public void updateaiEntityList() {
//        for (Entity entity : aiEntityList) {
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
