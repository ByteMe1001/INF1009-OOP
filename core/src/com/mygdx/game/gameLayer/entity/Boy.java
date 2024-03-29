package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.entity.PlayableEntity;
import com.mygdx.game.gameEngine.player.PlayerMovement;
import com.mygdx.game.gameEngine.sound.SoundEffectType;
import com.mygdx.game.gameEngine.sound.SoundManager;
import com.mygdx.game.gameLayer.movement.Player1MovementStrategy;

public class Boy extends PlayableEntity {

    // Additional properties for Boy class
    private final static String TEXTURE_PATH = "spaceship.png";
    private PlayerMovement playerMovementStrategy;
    private static final float BULLET_SPAWN_OFFSET_X = 20;
    private static final float BULLET_SPAWN_OFFSET_Y = 10;
    private EntityFactory entityFactory;
    private EntityManager entityManager;
    private static SoundManager soundManager;
    private Bullet bullet;

    private int powerUpLevel;

    // SHOOTING VARIABLES
    private float timeSeconds = 0;
    private static final float shootRate = 0.2f;

    // Default constructor
    public Boy() {
        //do nothing for now
    }

    // Constructor with ID
    public Boy(int health, int maxHealth, float x, float y, float scale, Sprite sprite,
              float speed, PlayerMovement playerMovement, SpriteBatch batch) {
        super(health, maxHealth, x, y, scale, sprite ,speed, playerMovement, batch);
        // TODO: upgrade to dynamic assignment of strat?
        this.playerMovementStrategy = new Player1MovementStrategy();
        soundManager = SoundManager.getInstance();
    }
    public Boy(int health, int maxHealth, float x, float y, float scale, Sprite sprite,
               float speed, PlayerMovement playerMovement, SpriteBatch batch,
               EntityFactory entityFactory, EntityManager entityManager) {
        super(health, maxHealth, x, y, scale, sprite, speed, playerMovement, batch);
        this.entityFactory = entityFactory;
        this.entityManager = entityManager;
        soundManager = SoundManager.getInstance();
        this.powerUpLevel = 0;
        this.playerMovementStrategy = new Player1MovementStrategy();
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~GETTER AND SETTER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public PlayerMovement getPlayerMovementStrategy() {
        return playerMovementStrategy;
    }

    public void setPlayerMovementStrategy(PlayerMovement playerMovementStrategy) {
        this.playerMovementStrategy = playerMovementStrategy;
    }

    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

    public void setEntityFactory(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public int getPowerUpLevel() {
        return powerUpLevel;
    }

    public void setPowerUpLevel(int powerUpLevel) {
        this.powerUpLevel = powerUpLevel;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~GAME LOGIC~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public void update() {
        //movement();
        super.update();
    }

    @Override
    public void takeDamage(int damage) {
        int updatedHealth = getHealth() - damage;
        setHealth(updatedHealth);
        //System.out.println("Health: " + getHealth());
        // Handle damage logic for the Boy
    }

    @Override
    public void heal(int amount) {
        // Handle healing logic for the Boy

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
        // Handle destruction logic for the Boy
    }


    // TO USE
    public void movement(String direction) {
        float [] vector = playerMovementStrategy.calculateMovement(this, direction);
        super.movement(vector);
    }
    //Shoot function
    public void shoot() {
        // Calculate the spawn position of the bullet relative to the Boy's position
        float bulletSpawnX = getX() + BULLET_SPAWN_OFFSET_X;
        float bulletSpawnY = getY() + BULLET_SPAWN_OFFSET_Y;

        // Call the shoot method of the Boy instance
        timeSeconds += Gdx.graphics.getDeltaTime();
        if(timeSeconds > shootRate) {
            timeSeconds -= shootRate;
            Entity bullet = getEntityFactory().createEntity(3, 1, bulletSpawnX, bulletSpawnY);
            soundManager.playSE(SoundEffectType.FIRE);
        }
    }

}


//    public void movement(float[] vector) {
//        if (getControl() == 'P') {
//            float[] vector = playerControlManager.movement(this.getX(), this.getY(), this.getSpeed());
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


    // Initialize Boy properties
//    private void initializeBucket(GamePlayerManager playerControlManager) {
//        this.playerControlManager = playerControlManager;
//        this.setSprite(new Sprite(new Texture(BoyType.DEFAULT.getTexturePath())));
//        this.setAlive(BoyType.DEFAULT.isAlive());
//        this.setCollidable(BoyType.DEFAULT.isCollidable());
//        this.setControl(BoyType.DEFAULT.getControl());
//    }
