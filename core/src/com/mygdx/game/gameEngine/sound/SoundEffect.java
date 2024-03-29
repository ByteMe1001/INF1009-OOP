package com.mygdx.game.gameEngine.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {
    private Sound sound;        // Sound object

    // Constructor with fileName
    public SoundEffect(String fileName) {
        try {
            this.sound = Gdx.audio.newSound(Gdx.files.internal(fileName));
        } catch (Exception e) {
            System.out.println("Error loading sound file: " + fileName);
        }
    }

    // Play sound
    public void play() {
        if (sound != null) {
            sound.play();
        }
    }

    // Dispose sound
    public void dispose() {
        if (sound != null) {
            sound.dispose();
        }
    }
}
