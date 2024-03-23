package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


// TODO: I dont think we need it sorry
public abstract class NonCollidableEntities extends Entity {

    // Constructors
    public NonCollidableEntities() {
        // No-arg constructor
    }

    public NonCollidableEntities(SpriteBatch batch) {
        super(batch);
    }

    public NonCollidableEntities(float x, float y, float scale, Sprite sprite, SpriteBatch batch) {
        super(x, y, scale, sprite, batch);
    }

    // Abstract Methods

    // Method Overrides
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void draw() {
        super.draw();
    }
}
