package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DisplayElement extends Entity {


    // Constructors
    public DisplayElement() {
        // No-arg constructor
    }

    public DisplayElement(SpriteBatch batch) {
        super(batch);
    }

    public DisplayElement(float x, float y, float scale, Sprite sprite, SpriteBatch batch) {
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
