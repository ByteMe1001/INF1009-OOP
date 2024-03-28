package com.mygdx.game.gameEngine.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class backgroundMusic {
    private Music music;
    private float position;

    public backgroundMusic(String fileName) {
        try{
            this.music = Gdx.audio.newMusic(Gdx.files.internal(fileName));
        } catch (Exception e) {
            System.out.println("Error loading music file: " + fileName);
        }
    }

    public void play() {
        if (music != null) {
            music.setPosition(position);
            music.play();
            //music.setLooping(true);
        }
    }

    public void pause() {
        if (music != null) {
            music.pause();
            position = music.getPosition();
        }
    }

    public void stop() {
        if (music != null) {
            music.stop();
            position = 0;
        }
    }

    public void resume() {
        if (music != null) {
            music.play();
        }
    }

    public void dispose() {
        if (music != null) {
            music.dispose();
        }
    }
}
