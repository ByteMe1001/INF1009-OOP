package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ai.AiControlManager;
import com.mygdx.game.util.iAiMovement;

public class Bullet extends Entity implements iAiMovement {

    // Texture path for the bullet
    private static final String TEXTURE_PATH = "bullet1.png";

    private AiControlManager aiControlManager;

    // Default constructor
    protected Bullet() {
        // Do nothing for now
    }

    // Constructor with ID parameter
    protected Bullet(int id, SpriteBatch batch) {
       super (id,batch);
       this.setChangeRate(BulletType.DEFAULT.getChangeRate());
       this.setSprite(new Sprite((new Texture(BulletType.DEFAULT.getTexturePath()))));
       this.setAlive(BulletType.DEFAULT.isAlive());
       this.setCollidable(BulletType.DEFAULT.isCollidable());
       this.setControl(BulletType.DEFAULT.getControl());
    }

    // Parameterized constructor
    protected Bullet(AiControlManager aiControlManager, int id, int health, float x, float y, float scale, float width, float height, float speed, int direction, SpriteBatch batch) {
        super(id, health, x, y, scale, new Sprite(new Texture(BulletType.DEFAULT.getTexturePath())), width, height, speed, direction, batch);
        setWidth(getSprite().getWidth());
        setHeight(getSprite().getHeight());
        this.aiControlManager = aiControlManager;
        this.setChangeRate(BulletType.DEFAULT.getChangeRate());
        this.setAlive(BulletType.DEFAULT.isAlive());
        this.setCollidable(BulletType.DEFAULT.isCollidable());
        this.setControl(BulletType.DEFAULT.getControl());
    }
    @Override
    protected void update() {
        movement();
        super.getBoundingBox().setPosition(getX(), getY());
    }
    @Override
    protected void takeDamage(int damage) {

    }

    @Override
    protected void heal(int amount) {

    }

    @Override
    protected void destroy() {

    }

    @Override
    public void setLeftRight() {

    }

    @Override
    public void setUpDown() {

    }

    @Override
    public void setAll() {

    }
    //Movement Logic

    @Override
    protected void movement() {
        // Move the bullet upwards
        float newY = getY() + getSpeed(); // Adjust speed as needed
        setY(newY);

        // Get the height of the screen
        int screenHeight = Gdx.graphics.getHeight();

        // Check if the bullet is out of bounds (reached the top edge of the screen)
        if (newY > screenHeight) {
            // If the bullet reaches the top edge, destroy it
            destroy();
        }
    }

}