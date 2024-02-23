package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Player;
import com.mygdx.game.util.iIO;

public class PlayerControlManager implements iIO {

    private float x, y, speed;


    public PlayerControlManager() {

    }

    public PlayerControlManager(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    // Check for input keys to return movement vector, can user WASD (inputKey1()) or UDLR keys (inputKey2())
    public float[] movement(float x, float y, float speed) {
        float [] vector = new float[2];     // [0] is X axis, [1] is Y axis

        switch(inputKey1()) {       // inputKey1() or inputKey2()

            case "FALSE":
                vector[0] = x;
                vector[1] = y;
                break;
            case "UP":
                vector[0] = x;
                vector[1] = (y + (speed * Gdx.graphics.getDeltaTime()));
                break;
            case "DOWN":
                vector[0] = x;
                vector[1] = (y - (speed * Gdx.graphics.getDeltaTime()));
                break;
            case "LEFT" :
                vector[0] = (x - (speed * Gdx.graphics.getDeltaTime()));
                vector[1] = y;
                break;
            case "RIGHT" :
                vector[0] = (x + (speed * Gdx.graphics.getDeltaTime()));
                vector[1] = y;
            break;
            default:
                break;
        }
        return vector;
    }

    // Getter and setter methods
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
