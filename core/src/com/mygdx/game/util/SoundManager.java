package com.mygdx.game.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;



public class SoundManager {
    private HashMap<String, Integer> sceneSoundMap;
    private ArrayList<Clip> clips;
    private URL[] soundURL = new URL[2];
    private boolean isMusicPlaying = false;

    public SoundManager() {
        clips = new ArrayList<>();
        sceneSoundMap = new HashMap<>();
        soundURL[0] = getClass().getResource("/Ground_Theme.wav");
        soundURL[1] = getClass().getResource("/SkyFire.wav");
        sceneSoundMap.put("StartingScene", 0);
        sceneSoundMap.put("GameScene", 1);
    }

    // Load track into clip array list
    private void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clips.add(clip);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Play according to index
    public void play(int i) {
        if (i >= 0 && i < clips.size()) {
            Clip clip = clips.get(i);
            clip.setFramePosition(0);
            clip.setMicrosecondPosition(0);
            clip.start();
            isMusicPlaying = true;
        } else {
            System.out.println("Invalid index: " + i);
        }
    }

    // Loop music
    public void loop() {
        clips.forEach(clip -> clip.loop(Clip.LOOP_CONTINUOUSLY));
    }

    // Stop according to index
    public void stop(int i) {
        if (i >= 0 && i < clips.size()) {
            Clip clip = clips.get(i);
            clip.stop();
            isMusicPlaying = false;
        } else {
            System.out.println("Invalid index: " + i);
        }
    }

    // Stop all music
    public void stopAll() {
        clips.forEach(Clip::stop);
        isMusicPlaying = false;
    }

    // Play sound effect
    public void playSE(String sceneName) {
        Integer i = sceneSoundMap.get(sceneName);
        setFile(i);
        play(i);
    }

    // Play music
    public void playMusic(String sceneName) {
        Integer i = sceneSoundMap.get(sceneName);
        setFile(i);
        play(i);
        loop();
    }

    public boolean isMusicPlaying() {
        return isMusicPlaying;
    }

    // Dispose resources
    public void dispose() {
        stopAll();
        clips.forEach(Clip::close);
    }
}
