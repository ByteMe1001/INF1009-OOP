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


    // To prevent concurrent music playing
    public boolean isMusicPlaying() {
        return isMusicPlaying;
    }
}
