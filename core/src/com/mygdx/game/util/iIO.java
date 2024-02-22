package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface iIO {
    // Default method to detect W, A, S, D keys
    default String inputKey() {

        if (Gdx.input.isKeyPressed(Keys.W)) {
            return "UP";
        }

        else if (Gdx.input.isKeyPressed(Keys.S)) {
            return "DOWN";
        }

        else if (Gdx.input.isKeyPressed(Keys.A)) {
            return "LEFT";
        }

        else if (Gdx.input.isKeyPressed(Keys.D)) {
            return "RIGHT";
        }

        else return "FALSE";
    }

    // Method for arrow key
    default String inputKey2() {

        if (Gdx.input.isKeyPressed(Keys.UP)) {
            return "UP";
        }

        else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            return "DOWN";
        }

        else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            return "LEFT";
        }

        else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            return "RIGHT";
        }

        else return "FALSE";
    }

    // Default method to draw text on a SpriteBatch using a BitmapFont
    default void drawText(SpriteBatch batch, BitmapFont font, String text, float x, float y) {
        font.draw(batch, text, x, y);
    }

    // Default method to draw text on a SpriteBatch using a BitmapFont with specified scale
    default void drawText(SpriteBatch batch, BitmapFont font, String text, float x, float y, float scaleX, float scaleY) {
        font.getData().setScale(scaleX, scaleY);
        font.draw(batch, text, x, y);
        font.getData().setScale(1, 1); // Reset scale to default
    }

    // Default method to draw a shape using a ShapeRenderer

    // Default method to draw a shape using a ShapeRenderer
    default void drawShape(ShapeRenderer shapeRenderer, String shapeType, float x, float y, float width, float height, Color color) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        switch (shapeType) {
            case "Circle":
                shapeRenderer.setColor(color);
                shapeRenderer.circle(x, y, width / 2);
                break;
            case "Rectangle":
                shapeRenderer.setColor(color);
                shapeRenderer.rect(x, y, width, height);
                break;
            default:
                // Handle unknown shape types
                break;
        }
    }
}