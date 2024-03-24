package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameEngine.ai.AiControlManager;
import com.mygdx.game.gameEngine.util.iAiMovement;
import com.mygdx.game.gameLayer.movement.*;

import java.util.ArrayList;

public class EnemyBullet extends CollidableEntities implements iAiMovement {

    // Texture path for the bullet
    private static final String TEXTURE_PATH = "81_pizza.png";
    //private static final int DEFAULT_CHANGE_RATE = 0;
    private int changeRate;
    private int defaultChangeRate;
    private AIMovementStrategy movementStrategy;
    private ArrayList<AIMovementStrategy> movementStrategyList;
    // Default constructor
    public EnemyBullet() {
        // Do nothing for now
    }

    // Constructor with ID parameter
    public EnemyBullet(SpriteBatch batch) {
        super(batch);
    }

    // Parameterized constructor
    public EnemyBullet(int health, float x, float y, float scale,
                  Sprite sprite, float speed, int defaultChangeRate, SpriteBatch batch) {
        super(health, x, y, scale, sprite, speed, batch);
        this.changeRate = 0;
        this.defaultChangeRate = defaultChangeRate;
        this.movementStrategyList = new ArrayList<AIMovementStrategy>();
        setMovementStrategy(new UpMovement());
        initializeMovementStrategy();
    }

    public void initializeMovementStrategy() {
        movementStrategyList.add(new LeftMovement());
        movementStrategyList.add(new RightMovement());
        movementStrategyList.add(new UpMovement());
        movementStrategyList.add(new DownMovement());
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

    @Override
    public void update() {
        super.update();
    }
    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public void heal(int amount) {

    }

    @Override
    public void destroy() {

    }

    public AIMovementStrategy getMovementStrategy() {
        return this.movementStrategy;
    }

    public void setMovementStrategy(AIMovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
    public AIMovementStrategy getMovementStrategy(String name) {
        for (AIMovementStrategy strategy : movementStrategyList) {
            if (strategy.getClass().getSimpleName().equals(name)) {
                return strategy;
            }
        }
        // Return null if no strategy with the given name is found
        throw new IllegalArgumentException("Movement Strategy not found in the list");
    }
    //Movement Logic
    public void movement() {
        setUp();
    }
    public void setUp() {
        setMovementStrategy(getMovementStrategy("UpMovement"));
    }

}
