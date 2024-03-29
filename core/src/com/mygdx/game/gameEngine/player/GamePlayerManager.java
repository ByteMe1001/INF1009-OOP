package com.mygdx.game.gameEngine.player;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.entity.PlayableEntity;
import com.mygdx.game.gameEngine.util.iIO;
import com.mygdx.game.gameEngine.util.iPlayerMovement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO: CHANGE TO PLAYABLE ENTITY
public class GamePlayerManager implements iIO {

    private ArrayList<PlayableEntity> playerEntityList;
    private HashMap<Integer, List<Integer>> healthDataMap;

    private EntityManager entityManager;

    // TODO: SHOULD NOT BE HERE
    private float timeSeconds = 0f;
    private float period = 0.2f;
    // END

    public GamePlayerManager() {

    }

    public GamePlayerManager(EntityManager entityManager, ArrayList<PlayableEntity> playerEntityList) {
        this.entityManager = entityManager;
        this.playerEntityList = playerEntityList;
        healthDataMap = new HashMap<>();
    }

    // PLease fix remove args
    public void update(EntityManager entityManager) {
        for (PlayableEntity e : playerEntityList) {
            e.movement(movement(e));
            shoot(e, e.getX(), e.getY());
            addHealthData();
        }
    }

    // Check for input keys to return movement vector, can user WASD (inputKey1()) or UDLR keys (inputKey2())
    public float[] movement(float x, float y, float speed) {
        float[] vector = new float[2];     // [0] is X axis, [1] is Y axis

        switch (inputKey1()) {               // inputKey1() or inputKey2()

            case "FALSE":                   // Default case if no input
                vector[0] = x;
                vector[1] = y;
                break;
            case "UP":
                vector[0] = x;
                vector[1] = (y + (speed * Gdx.graphics.getDeltaTime()));
                break;
            case "DOWN":
                vector[0] = x;
                vector[1] = (y - (speed * Gdx.graphics.getDeltaTime()));
                break;
            case "LEFT":
                vector[0] = (x - (speed * Gdx.graphics.getDeltaTime()));
                vector[1] = y;
                break;
            case "RIGHT":
                vector[0] = (x + (speed * Gdx.graphics.getDeltaTime()));
                vector[1] = y;
                break;
            default:
                break;
        }
        return vector;
    }

    // TODO: Add input key map according to string and inputkey function
    public String movement(PlayableEntity entity) {
        switch (inputKey1()) {               // inputKey1() or inputKey2()
            case "UP":
                return "UP";
            case "DOWN":
                return "DOWN";
            case "LEFT":
                return "LEFT";
            case "RIGHT":
                return "RIGHT";
            default:
                return "FALSE";
        }
    }

    // TODO: Move to BOY
    public void shoot(PlayableEntity entity, float x, float y) {
        switch (inputKey3()) {
            case "FALSE":
                break;
            case "SPACE":
                // Call the shoot method of the Boy instance
                timeSeconds += Gdx.graphics.getDeltaTime();
                if(timeSeconds > period) {
                    timeSeconds -= period;
                    entity.shoot(); // Assuming EntityManager is needed for bullet creation
                }
                break;
            default:
                break;
        }
    }


    public void addHealthData() {
        int key = 0;
        healthDataMap.clear();
        for (iPlayerMovement playerEntity : playerEntityList) {
            List<Integer> healthDataList = new ArrayList<>();
            healthDataList.add(playerEntity.getHealth());
            healthDataList.add(playerEntity.getMaxHealth());
            healthDataMap.put(key, healthDataList);
            key++;
        }
    }

    public List<Integer> getHealthData(int entityId) {
        return healthDataMap.get(entityId);
    }

    public HashMap<Integer, List<Integer>> getHealthDataMap() {
        return healthDataMap;
    }

    // Getter and setter methods
//    public float getX() {
//        return x;
//    }
//
//    public void setX(float x) {
//        this.x = x;
//    }
//
//    public float getY() {
//        return y;
//    }
//
//    public void setY(float y) {
//        this.y = y;
//    }
//
//    public float getSpeed() {
//        return speed;
//    }

//    public void setSpeed(float speed) {
//        this.speed = speed;
//    }
}
