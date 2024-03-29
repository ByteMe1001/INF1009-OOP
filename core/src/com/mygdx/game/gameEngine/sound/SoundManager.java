package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.gameLayer.sound.GameSoundTrack;

import java.net.URL;
import java.util.Map;

public class SoundManager {

    private static SoundManager instance = null;    // Singleton instance
    private SoundTrack soundTrack;
    private Map<String, URL> soundURLs;

    private boolean isMusicPlaying = false;

    private SoundManager(SoundTrack soundTrack) {
        this.soundTrack = soundTrack;
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager(new GameSoundTrack());
        }
        return instance;
    }

    // Overload instance
    public static SoundManager getInstance(SoundTrack soundTrack) {
        if (instance == null) {
            instance = new SoundManager(soundTrack);
        }
        return instance;
    }

    // Play sound effect
    public void playSE(SoundEffectType soundEffectType) {
        SoundEffect soundEffect = soundTrack.getSoundEffect(soundEffectType);
        if (soundEffect != null) {
            soundEffect.play();
        } else {
            throw new IllegalArgumentException("Effect name not found in sound map: " + soundEffectType);
        }
    }

    // Play music
    public void playMusic(String sceneName) {
        BackgroundMusic music = soundTrack.getBackgroundMusic(sceneName);
        if (music != null) {
            music.play();
            isMusicPlaying = true;
        } else {
            throw new IllegalArgumentException("Scene name not found in sound map: " + sceneName);
        }
    }

    // Stop all sounds and music
    public void stopAll() {
        soundTrack.stopAll();
        isMusicPlaying = false;
    }
    
    public void stopMusic(String sceneName) {
        BackgroundMusic music = soundTrack.getBackgroundMusic(sceneName);
        if (music != null) {
            music.stop();
            isMusicPlaying = false;
        } else {
            throw new IllegalArgumentException("Scene name not found in sound map: " + sceneName);
        }
    }

    // Pause all sounds and music
    public void pauseAll() {
        soundTrack.pauseAllBackgroundMusic();
        isMusicPlaying = false;
    }

    // Resume all music
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
