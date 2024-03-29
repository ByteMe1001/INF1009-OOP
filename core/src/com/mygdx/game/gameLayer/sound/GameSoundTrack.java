package com.mygdx.game.gameLayer.sound;

import com.mygdx.game.gameEngine.sound.SoundEffect;
import com.mygdx.game.gameEngine.sound.SoundEffectType;
import com.mygdx.game.gameEngine.sound.SoundTrack;
import com.mygdx.game.gameEngine.sound.BackgroundMusic;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

public class GameSoundTrack extends SoundTrack {
    private Map<String, BackgroundMusic> backgroundMusicMap;
    private Map<SoundEffectType, SoundEffect> soundEffectMap;

    public GameSoundTrack() {
        backgroundMusicMap = new HashMap<>();
        soundEffectMap = new HashMap<>();

        // BGM addition
        backgroundMusicMap.put("StartingScene", new BackgroundMusic("Ground_Theme.wav"));
        backgroundMusicMap.put("GameScene", new BackgroundMusic("SkyFire.wav"));
        // SFX addition
        soundEffectMap.put(SoundEffectType.BUTTON, new SoundEffect("button_sound.wav"));
        soundEffectMap.put(SoundEffectType.HIT, new SoundEffect("explosion.wav"));
        soundEffectMap.put(SoundEffectType.FIRE, new SoundEffect("laser.wav"));
        soundEffectMap.put(SoundEffectType.COLLECT, new SoundEffect("eatingSound.wav"));
    }

    public BackgroundMusic getBackgroundMusic(String sceneName) {
        return backgroundMusicMap.get(sceneName);
    }

    public SoundEffect getSoundEffect(SoundEffectType soundEffectType) {
        return soundEffectMap.get(soundEffectType);
    }

    // Pause all BGM
    public void pauseAllBackgroundMusic() {
        Collection<BackgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
        for (BackgroundMusic music : backgroundMusicCollection) {
            music.pause();
        }
    }

    // Resume all BGM
    public void resumeAllBackgroundMusic() {
        Collection<BackgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
        for (BackgroundMusic music : backgroundMusicCollection) {
            music.resume();
        }
    }

    // Stop specific BGM
    public void stopMusic(String sceneName) {
        BackgroundMusic music = getBackgroundMusic(sceneName);
        if (music != null) {
            music.stop();
        } else {
            throw new IllegalArgumentException("Scene name not found in sound map: " + sceneName);
        }
    }

    // Stop all sound
    public void stopAll() {
        Collection<BackgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
        for (BackgroundMusic music : backgroundMusicCollection) {
            music.stop();
        }
    }

    // Dispose method
    public void dispose() {
        Collection<BackgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
        for (BackgroundMusic music : backgroundMusicCollection) {
            music.dispose();
        }

        Collection<SoundEffect> soundEffectCollection = soundEffectMap.values();
        for (SoundEffect sound : soundEffectCollection) {
            sound.dispose();
        }
    }
}
