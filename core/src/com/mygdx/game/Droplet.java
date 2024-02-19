package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Droplet extends Entity {

    // Additional properties for Droplet class
    private static final float DROPLET_SPEED = 100.0f;

    // Default constructor
    public Droplet(int id) {
        super(id);
        this.setSprite(new Sprite(new Texture("droplet.png"))); 
        this.setAlive(true);
    }

    // Parameterized constructor
    public Droplet(int id, int health, float x, float y, float scale, Sprite sprite, int width ,int height) {
        super(id, health, x, y, scale, sprite, width, height);
        this.setAlive(true);
        Droplet droplet = new Droplet(id, health, boundingBox.x, boundingBox.y, scale, sprite, width, height);
    }

    @Override
    public void update() {
        // Handle update logic for the droplet (AI-controlled movement)
        this.setY(this.getY() - Gdx.graphics.getDeltaTime() * DROPLET_SPEED);

        // Reset droplet if it goes below the screen
        if (this.getY() + this.getHeight() < 0) {
            this.setY(Gdx.graphics.getHeight());
        }
    }



    @Override
    public void render() {
        // Rendering logic for the droplet
    }

    @Override
    public boolean collideWith(Entity other) {
        // Collision logic for the droplet with another entity
        return false;
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

}



