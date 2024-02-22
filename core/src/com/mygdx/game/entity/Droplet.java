package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AiControlManager;

import java.util.Random;

public class Droplet extends Entity {

    // Additional properties for Droplet class
    private static final float DROPLET_SPEED = 100.0f;
    private static final int DEFAULT_CHANGE_RATE = 30;
    private int changeRate;
    private AiControlManager aiControlManager;

    private SpriteBatch batch;

    // Default constructor
    public Droplet() {
        // do nothing don't touch
    }
    // Constructor with id parameter
    public Droplet(int id, SpriteBatch batch) {
        super(id, batch);
        //this.aiControlManager = new AiControlManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), getSpeed());
        this.setChangeRate(DEFAULT_CHANGE_RATE);
        this.setSprite(new Sprite(new Texture("droplet.png")));
        this.setAlive(true);
        this.setCollidable(true);
        this.setControl('A');
    }

    // Parameterized constructor
    public Droplet(AiControlManager aiControlManager, int id, int health, float x, float y, float scale, float width, float height, int speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture("droplet.png")), width, height, speed, direction, batch);
        setWidth(getSprite().getWidth());
        setHeight(getSprite().getHeight());
        this.aiControlManager = aiControlManager;
        //this.aiControlManager = new AiControlManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), getSpeed());
        this.setAlive(true);
        this.setChangeRate(DEFAULT_CHANGE_RATE);
        //Droplet droplet = new Droplet(id, health, boundingBox.x, boundingBox.y, scale, sprite, width, height, speed);
        this.setCollidable(true);
        this.setControl('A');
    }

    @Override
    public void update() {
        movement();
        boundingBox.setPosition(this.getX(), this.getY());
        //System.out.println(boundingBox);
    }


    // not needed
    @Override
    public void render() {
        // Rendering logic for the droplet
    }

    /*
    @Override
    public boolean collidesWith(Entity other) {
        return getBoundingBox().overlaps(other.getBoundingBox());//if entity's bounding box overlaps another entities bounding box
    }
*/
    /*
    //@Override
    public void collideWith(Entity other) {
        // Collision logic for the droplet with another entity
        System.out.println("Collision Detected");

        //return false;
    }

     */

    @Override
    public int getChangeRate() {
        return changeRate;
    }

    @Override
    public int getDefaultChangeRate() {
        return DEFAULT_CHANGE_RATE;
    }

    @Override
    public void setChangeRate(int changeRate) {
        this.changeRate = changeRate;
    }

    @Override
    public void takeDamage(int damage) {
        // Handle damage logic for the droplet
    }

    @Override
    public void heal(int amount) {
        // Handle healing logic for the droplet
    }

    @Override
    public void destroy() {
        // Handle destruction logic for the droplet

    }

    @Override
    public void movement() {
        if (getControl() == 'A') {
            Random random = new Random();
            int randomNumber = random.nextInt(31);
            aiControlManager.movement(this, this.getX(), this.getY(), this.getWidth(), this.getHeight(), randomNumber);
        }
    }
}




