package com.mygdx.game.gameEngine.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoundManager {
    private Map<String, URL> soundURLs;
    private Map<String, List<Clip>> clips;
    private boolean isMusicPlaying = false;
    private Map<Clip, Long> clipPositions = new HashMap<>();

    public SoundManager() {
        soundURLs = new HashMap<>();
        soundURLs.put("StartingScene", getClass().getResource("/Ground_Theme.wav"));
        soundURLs.put("StartingScene_Button", getClass().getResource("/button_sound.wav"));
        soundURLs.put("GameScene", getClass().getResource("/SkyFire.wav"));
        soundURLs.put("GameScene_Collision", getClass().getResource("/explosion.wav"));

        clips = new HashMap<>();
    }

    // Load track into clip map
    private void loadClip(String sceneName) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURLs.get(sceneName));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);

            List<Clip> clipList = clips.computeIfAbsent(sceneName, k -> new ArrayList<>());
            clipList.add(clip);
        } catch (Exception e) {
            System.out.println("Error loading sound for scene " + sceneName + ": " + e.getMessage());
        }
    }

    // Play sound effect
    public void playSE(String sceneName) {
        if (soundURLs.containsKey(sceneName)) {
            try {
                if (!clips.containsKey(sceneName)) {
                    loadClip(sceneName);
                }
                List<Clip> clipList = clips.get(sceneName);
                for (Clip clip : clipList) {
                    clip.setFramePosition(0);
                    clip.start();
                }
            } catch (Exception e) {
                System.out.println("Error playing sound for scene " + sceneName + ": " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Scene name not found in sound map: " + sceneName);
        }
    }

    // Play music
    public void playMusic(String sceneName) {
        if (soundURLs.containsKey(sceneName)) {
            try {
                if (!clips.containsKey(sceneName)) {
                    loadClip(sceneName);
                }
                List<Clip> clipList = clips.get(sceneName);
                for (Clip clip : clipList) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                isMusicPlaying = true;
            } catch (Exception e) {
                System.out.println("Error playing music for scene " + sceneName + ": " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Scene name not found in sound map: " + sceneName);
        }
    }

    // Stop sound or music
    public void stop(String sceneName) {
        if (clips.containsKey(sceneName)) {
            List<Clip> clipList = clips.get(sceneName);
            for (Clip clip : clipList) {
                clip.stop();
            }
            isMusicPlaying = false;
        } else {
            throw new IllegalArgumentException("No sound or music playing for scene: " + sceneName);
        }
    }

    // Stop all sounds and music
    public void stopAll() {
        for (List<Clip> clipList : clips.values()) {
            for (Clip clip : clipList) {
                clip.stop();
            }
        }
        isMusicPlaying = false;
    }

//    pause logic
//     Pause sound or music (NOT USES,,YET)
//    public void pause(String sceneName) {
//        if (clips.containsKey(sceneName)) {
//            List<Clip> clipList = clips.get(sceneName);
//            for (Clip clip : clipList) {
//                clip.stop(); // Stop the clip
//                clip.setMicrosecondPosition(0); // Reset the clip position to start from the beginning when resumed
//            }
//        } else {
//            throw new IllegalArgumentException("No sound or music playing for scene: " + sceneName);
//        }
//    }

    // Pause all sounds and music
    public void pauseAll() {
        for (List<Clip> clipList : clips.values()) {
            for (Clip clip : clipList) {
                if (clip.isRunning()) { // Check if the clip is running before pausing
                    long position = clip.getMicrosecondPosition(); // Get the current position
                    clipPositions.put(clip, position); // Store the position for later use
                    clip.stop(); // Stop the clip
                }
            }
        }
        isMusicPlaying = false;
    }

    public void resumeAll() {
        for (Map.Entry<Clip, Long> entry : clipPositions.entrySet()) {
            Clip clip = entry.getKey();
            Long position = entry.getValue();
            clip.setMicrosecondPosition(position); // Set the clip position to the stored position
            clip.start(); // Start the clip from the stored position
        }
        isMusicPlaying = true;
    }



    // Dispose resources
    public void dispose() {
        for (List<Clip> clipList : clips.values()) {
            for (Clip clip : clipList) {
                clip.close();
            }
        }
        clips.clear();
    }

    // To prevent concurrent music playing
    public boolean isMusicPlaying() {
        return isMusicPlaying;
    }
}
