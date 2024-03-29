package com.mygdx.game.gameEngine.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusic {
    private Music music;        // Music object
    private float position;     // Set position to resume music

    // Constructor with fileName
    public BackgroundMusic(String fileName) {
        try{
            this.music = Gdx.audio.newMusic(Gdx.files.internal(fileName));
        } catch (Exception e) {
            System.out.println("Error loading music file: " + fileName);
        }
    }

    // Play music
    public void play() {
        if (music != null) {
            music.setPosition(position);        // Set position to resume music
            music.play();
        }
    }

    // Pause music
    public void pause() {
        if (music != null) {
            music.pause();
            position = music.getPosition();     // Save play position
        }
    }

    // Stop music
    public void stop() {
        if (music != null) {
            music.stop();
            position = 0;                       // Reset play position
        }
    }

    // Resume music
    public void resume() {
        if (music != null) {
            music.play();
        }
    }

    // Dispose music
    public void dispose() {
        if (music != null) {
            music.dispose();
        }
    }
}
