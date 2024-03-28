package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.gameLayer.sound.GameSoundTrack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoundManager {

    private static SoundManager instance = null;
    private SoundTrack soundTrack;
    private Map<String, URL> soundURLs;
    private Map<String, List<Clip>> clips;
    private boolean isMusicPlaying = false;

    // TODO: weird position
    private Map<Clip, Long> clipPositions = new HashMap<>();


    // TODO: THIS IS HARDCODE NEED CHANGE
    private SoundManager(SoundTrack soundTrack) {
        this.soundTrack = soundTrack;
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

    public static SoundManager getInstance(SoundTrack soundTrack) {
        if (instance == null) {
            instance = new SoundManager(soundTrack);
        }
        return instance;
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager(new GameSoundTrack());
        }
        return instance;
    }

    // Play sound effect
    // Play sound effect
    public void playSE(String effectName) {
        SoundEffect soundEffect = soundTrack.getSoundEffect(effectName);
        if (soundEffect != null) {
            soundEffect.play();
        } else {
            throw new IllegalArgumentException("Effect name not found in sound map: " + effectName);
        }
    }

    // Play music
    public void playMusic(String sceneName) {
        backgroundMusic music = soundTrack.getBackgroundMusic(sceneName);
        if (music != null) {
            music.play();
            isMusicPlaying = true;
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

    // TODO: change to pause logic
    // Pause all sounds and music
    public void pauseAll() {
        soundTrack.pauseAllBackgroundMusic();
        isMusicPlaying = false;
    }

    // TODO: Change to resume logic
    public void resumeAll() {
        soundTrack.resumeAllBackgroundMusic();
        isMusicPlaying = true;
    }

    // Dispose resources
    public void dispose() {
        soundTrack.dispose();
    }

    // To prevent concurrent music playing
    public boolean isMusicPlaying() {
        return isMusicPlaying;
    }
}
