//SoundManager class

package com.mygdx.game.util;
import javax.sound.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.FileNotFoundException;
import java.net.URL;




public class SoundManager {
    Clip clip;
    URL[] soundURL = new URL[2];

    private boolean isMusicPlaying = false;

    // Other fields and methods...

    public SoundManager() {
        //Here can store the different sound in the soundURL array
        soundURL[0] = getClass().getResource("/Ground_Theme.wav");
        soundURL[1] = getClass().getResource("/Who_Let_The_Dogs_Out.wav");
    }

    public void setFile(int i) {
        try {
            // Attempt to open the audio file
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            System.out.println("Error: " + e.getMessage());
        }
    }

    //The play function to play the sound
    public void play() {
        clip.setFramePosition(0);
        clip.setMicrosecondPosition(0);
        clip.start();
        isMusicPlaying = true;
    }

    //The loop function to constantly play sound like for background music
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    //The stop function to stop the sound

    public void stop() {
        clip.stop();
        isMusicPlaying = false;
    }
    public void playSE(int i) {
        setFile(i);
        play();
    }

    public void playMusic(int i) {
        setFile(i);
        play();
        loop();
    }

    public void stopMusic(int i) {
       stop();
    }

    public boolean isMusicPlaying() {
        return isMusicPlaying;
    }
}


//This function is to put in either screen manager or enitity manager for either sound effect e.g. wheh  there is collision

