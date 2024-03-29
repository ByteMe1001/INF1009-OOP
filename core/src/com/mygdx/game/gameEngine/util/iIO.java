package com.mygdx.game.gameEngine.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.gameEngine.sound.SoundManager;


public interface iIO {
    // Default method to detect W, A, S, D keys
    default String inputKey1() {

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

    default String inputKey3() {
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            return "SPACE";
        }
        else return "FALSE";
    }

    // Method to handle button hover events
    default void addCursorEnterListener(Actor actor, SoundManager soundManager, String musicKey) {
        actor.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                soundManager.playSE(musicKey);
            }
        });
    }

    // Method to handle button click events
    default void addButtonClickListener(Actor button, Runnable onClick) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick.run(); // Execute the logic when the button is clicked
            }
        });
    }

    // Output

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
    default void drawShape(ShapeRenderer shapeRenderer, String shapeType, float x, float y, float width, float height, Color color) {
        switch (shapeType) {
            case "CIRCLE":
                shapeRenderer.setColor(color);
                shapeRenderer.circle(x, y, width / 2);
                break;
            case "RECTANGLE":
                shapeRenderer.setColor(color);
                shapeRenderer.rect(x, y, width, height);
                break;
            default:
                // Handle unknown shape types
                break;
        }
    }
}