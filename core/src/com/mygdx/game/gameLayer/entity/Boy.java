package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.ai.AiControlManager;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityFactory;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.entity.PlayableEntity;
import com.mygdx.game.gameEngine.player.PlayerControlManager;
import com.mygdx.game.gameEngine.player.PlayerMovement;
import com.mygdx.game.gameLayer.movement.Player1MovementStrategy;

public class Boy extends PlayableEntity {

    // Additional properties for Boy class
    private final static String TEXTURE_PATH = "spaceship.png";
    private PlayerMovement playerMovementStrategy;
    private static final float BULLET_SPAWN_OFFSET_X = 20;
    private static final float BULLET_SPAWN_OFFSET_Y = 10;
    private EntityFactory entityFactory;
    private EntityManager entityManager;
    private Bullet bullet;

    // Default constructor
    public Boy() {
        //do nothing for now
    }

    // Constructor with ID
    public Boy(SpriteBatch batch) {
        super(batch);
    }

    // Constructor with ID
    public Boy(int health, float x, float y, float scale, Sprite sprite,
              float speed, PlayerMovement playerMovement, SpriteBatch batch) {
        super(health, x, y, scale, sprite ,speed, playerMovement, batch);
        // TODO: upgrade to dynamic assignment of strat?
        this.playerMovementStrategy = new Player1MovementStrategy();
    }
    public Boy(int health, float x, float y, float scale, Sprite sprite,
               float speed, PlayerMovement playerMovement, SpriteBatch batch,
               EntityFactory entityFactory, EntityManager entityManager) {
        super(health, x, y, scale, sprite, speed, playerMovement, batch);
        this.entityFactory = entityFactory;
        this.entityManager = entityManager;
        System.out.println(entityManager);
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

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~GAME LOGIC~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public void update() {
        //movement();
        super.update();
    }

    @Override
    public void takeDamage(int damage) {
        int i = getHealth() - damage;
        setHealth(i);
        //System.out.println("Health: " + getHealth());
        // Handle damage logic for the Boy
    }

    @Override
    public void heal(int amount) {
        // Handle healing logic for the Boy
    }

    @Override
    public void destroy() {
        // Handle destruction logic for the Boy
    }

    @Override
    // Movement method for player with movement direction lock
    public void movement() {

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

        // Create a new bullet instance and add it to the entity manager
        Entity bullet = getEntityFactory().shootBullets(bulletSpawnX, bulletSpawnY);
        System.out.println(bullet);
        getEntityManager().addEntity(bullet);
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
//    private void initializeBucket(PlayerControlManager playerControlManager) {
//        this.playerControlManager = playerControlManager;
//        this.setSprite(new Sprite(new Texture(BoyType.DEFAULT.getTexturePath())));
//        this.setAlive(BoyType.DEFAULT.isAlive());
//        this.setCollidable(BoyType.DEFAULT.isCollidable());
//        this.setControl(BoyType.DEFAULT.getControl());
//    }
