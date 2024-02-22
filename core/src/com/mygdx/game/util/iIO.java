package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public interface iIO {
//    void keyPressed(int keycode);
//    void keyReleased(int keycode);

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
}