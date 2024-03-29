package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameEngine.ai.AiControlManager;
import com.mygdx.game.gameEngine.util.iAiMovement;
import com.mygdx.game.gameLayer.movement.*;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.entity.Entity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Enemy extends CollidableEntities implements iAiMovement{

    // Additional properties for Droplet class
    private final static String TEXTURE_PATH = "Boss.png";
    //private static final int DEFAULT_CHANGE_RATE = 30;
    private int changeRate;      // FOR AI MOVEMENT
    private int defaultChangeRate;
    private SpriteBatch batch;

    // For bullets stuff
    private EntityFactory entityFactory;
    private EntityManager entityManager;
    private float shootTimer = 0;
    private float shootInterval;
    private Random random = new Random();


    private AIMovementStrategy movementStrategy;
    private ArrayList<AIMovementStrategy> movementStrategyList;



    // Default constructor
    public Enemy() {
        // do nothing don't touch
    }
//    public Boss(int health, float x, float y, float scale, Sprite sprite,
//                float width, float height, float speed, int direction,
//                SpriteBatch batch) {
//        super(health, x, y, scale, sprite, speed, batch);
//        initializeMovementStrategy();
//    }
//
//    // Additional constructor if needed
//    public Boss(int id, AiControlManager aiControlManager, SpriteBatch batch) {
//        super(id, 100, 0, 0, 1f, new Sprite(new Texture(TEXTURE_PATH)), 64f, 64f, 300f, 3, batch);
//        this.aiControlManager = aiControlManager;
//        initializeMovementStrategy();
//    }

    // Additional constructor if needed
    public Enemy(int health, float x, float y, float scale, Sprite sprite,
                float speed, int defaultChangeRate,
                SpriteBatch batch, EntityFactory entityFactory, EntityManager entityManager) {
        super(health, x, y, scale, sprite, speed, batch);
        this.setY(Math.max(y, Gdx.graphics.getHeight() / 2)); //spawns only at top half
        this.changeRate = 0;
        this.defaultChangeRate = defaultChangeRate;
        this.movementStrategyList = new ArrayList<AIMovementStrategy>();
        initializeMovementStrategy();
        this.entityFactory = entityFactory;
        this.entityManager = entityManager;
        this.shootInterval = 1 + random.nextFloat() * (5 - 1); //random shoot value 1 to 5
    }

    //Shoot stuff
    public void shoot() {
        float bulletSpawnX = getX() + getSprite().getWidth() / 2;
        float bulletSpawnY = getY();
        entityFactory.createEntity(4, 1, bulletSpawnX, bulletSpawnY);;
    }


    // Getter and setter methods
    @Override
    public void update() {
        //movementStrategy.move(this);
        super.update();
        shootTimer += Gdx.graphics.getDeltaTime();
            if (shootTimer >= shootInterval) {
                shoot();
                shootTimer = 0; // Reset the timer after shooting
                shootInterval = 1 + random.nextFloat() * (5 - 1);
            }
            if (getY() < Gdx.graphics.getHeight() / 2.0f) {
                setY(Gdx.graphics.getHeight() / 2.0f);
            }
    }

    public void initializeMovementStrategy() {
        movementStrategyList.addAll(Arrays.asList(new LeftMovement(),
                new RightMovement(), new UpMovement(), new DownMovement()));
    }

    public void setMovementStrategy(AIMovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    // Get AI Movement strat
    public AIMovementStrategy getMovementStrategy() {
        return this.movementStrategy;
    }

    // Overloaded method
    public AIMovementStrategy getMovementStrategy(String name) {
        for (AIMovementStrategy strategy : movementStrategyList) {
            if (strategy.getClass().getSimpleName().equals(name)) {
                return strategy;
            }
        }
        // Return null if no strategy with the given name is found
        throw new IllegalArgumentException("Movement Strategy not found in the list");
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~AI CODE BLOCK~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public int getChangeRate() {
        return this.changeRate;
    }

    public int getDefaultChangeRate() {
        return defaultChangeRate;
    }

    @Override
    public void setChangeRate(int changeRate) {
        this.changeRate = changeRate;
    }

    public void decrementChangeRate() {
        --this.changeRate;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~AI CODE BLOCK ENDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // Game design logic
    @Override
    public void takeDamage(int damage) {
        // Subtract the damage from the enemy's health
        int updatedHealth = getHealth() - damage;
        setHealth(updatedHealth);

        // Check if the enemy is dead
        if (getHealth() <= 0) {
            // Determine if a HealthPack should spawn
            float chance = random.nextFloat(); 
            float spawnProbability = 0.50f; // 50% chance to spawn a HealthPack

            if (chance <= spawnProbability) {
                float x = getX();
                float y = getY();
                entityFactory.createEntity(5, 1, x, y); 
            }
        }
    }

    @Override
    public void heal(int amount) {
        // Handle healing logic for the Boss
        if (amount >= 0) {

            int healedHealth = getHealth() + amount;
            if (healedHealth > 100) {
                setHealth(100);
            } else {
                setHealth(healedHealth);
            }
        }
    }

    @Override
    public void destroy() {
        // Handle destruction logic for the Boss

    }

    // Dunnid probably
    // Movement logic

    public void movement() {
        setAll();
    }

    // Dunnid probably
    public void movement(float[] vector) {
//        if (getControl() == 'A') {
//            switch (super.getMovementSetID()) {         // 1 for up down, 2 for left right, 3 for all
//                case 1:
//                    setY(vector[1]);    // Set Y value
//                    break;
//                case 2:
//                    setX(vector[0]);    //Set X value
//                    break;
//                case 3:
//                    setX(vector[0]);    //Set X value
//                    setY(vector[1]);    // Set Y value
//                    break;
//            }
//        }
    }


    public void setAll() {
        // Check if entity has finished moving if so change direction
        if (getChangeRate() <= 0) {
            int randomNumber = random.nextInt(4); // Random number between 0 and 3 for four directions
            switch (randomNumber) {
                case 0:
                    setMovementStrategy(getMovementStrategy("LeftMovement"));
                    break;
                case 1:
                    setMovementStrategy(getMovementStrategy("RightMovement"));
                    break;
                case 2:
                    setMovementStrategy(getMovementStrategy("UpMovement"));
                    break;
                case 3:
                    setMovementStrategy(getMovementStrategy("DownMovement"));
                    break;
                default:
                    break;
            }
        }
        decrementChangeRate();
    }

    // TODO: TO CLEAR AFTER FINALISING
    public void setLeftRight() {
        if (getChangeRate() <= 0) {
            int randomNumber = random.nextInt(2);
            switch (randomNumber) {
                case 0:
                    setMovementStrategy(getMovementStrategy("LeftMovement"));
                    break;
                case 1:
                    setMovementStrategy(getMovementStrategy("RightMovement"));
                    break;
                default:
                    break;
            }
        }
        decrementChangeRate();
    }

    // TODO: TO CLEAR AFTER FINALISING
    public void setUpDown() {
        // Check if entity has finished moving
        if (getChangeRate() <= 0) {
            int randomNumber = random.nextInt(2);
            switch (randomNumber) {
                case 0:
                    setMovementStrategy(getMovementStrategy("UpMovement"));
                    break;
                case 1:
                    setMovementStrategy(getMovementStrategy("DownMovement"));
                    break;
                default:
                    break;
            }
        }
        decrementChangeRate();
    }

    public void setUp() {
        setMovementStrategy(getMovementStrategy("UpMovement"));
    }

    public void setDown() {

    }
    public void setLeft() {

    }
    public void setRight(){

    }
}




//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~SPARE CODE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
