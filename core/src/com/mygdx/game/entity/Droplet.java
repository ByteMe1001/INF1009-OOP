package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ai.AiControlManager;
import com.mygdx.game.util.iAiMovement;

import java.util.Random;

class Droplet extends CollidableEntities implements iAiMovement {

    // Additional properties for Droplet class
    private final static String TEXTURE_PATH = "asteroid.png";
    private static final int DEFAULT_CHANGE_RATE = 30;
    private int changeRate;
    private AiControlManager aiControlManager;
    private SpriteBatch batch;

    // Default constructor
    protected Droplet() {
        // do nothing don't touch
    }
    // Constructor with id parameter
    protected Droplet(int id, SpriteBatch batch) {
        super(id, batch);
        this.setChangeRate(DropletType.DEFAULT.getChangeRate());
        this.setSprite(new Sprite(new Texture(DropletType.DEFAULT.getTexturePath())));
        this.setAlive(DropletType.DEFAULT.isAlive());
        this.setCollidable(DropletType.DEFAULT.isCollidable());
        this.setControl(DropletType.DEFAULT.getControl());
    }

    // Parameterized constructor
    protected Droplet(AiControlManager aiControlManager, int id, int health, float x, float y, float scale, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture(DropletType.DEFAULT.getTexturePath())), width, height, speed, direction, batch);
        setWidth(getSprite().getWidth());
        setHeight(getSprite().getHeight());
        this.aiControlManager = aiControlManager;
        this.setChangeRate(DropletType.DEFAULT.getChangeRate());
        this.setAlive(DropletType.DEFAULT.isAlive());
        this.setCollidable(DropletType.DEFAULT.isCollidable());
        this.setControl(DropletType.DEFAULT.getControl());
    }

    // Getter and setter methods
    @Override
    protected void update() {
        movement();
        super.getBoundingBox().setPosition(getX(), getY());
    }

    @Override
    protected int getChangeRate() {
        return changeRate;
    }

    @Override
    protected int getDefaultChangeRate() {
        return DEFAULT_CHANGE_RATE;
    }

    @Override
    protected void setChangeRate(int changeRate) {
        this.changeRate = changeRate;
    }

    // Game design logic
    @Override
    protected void takeDamage(int damage) {
        // Handle damage logic for the droplet
    }

    @Override
    protected void heal(int amount) {
        // Handle healing logic for the droplet
    }

    @Override
    protected void destroy() {
        // Handle destruction logic for the droplet
    }
   
    
    private Random random = new Random();

    // Movement logic
    @Override
	public void movement() {
        if (getControl() == 'A') {
            Random random = new Random();
            int randomNumber = random.nextInt(31);
            aiControlManager.movement(this, this.getX(), this.getY(), this.getWidth(), this.getHeight(), randomNumber);
        }
    }

}
