package com.mygdx.game.gameLayer.sound;

import com.mygdx.game.gameEngine.sound.SoundEffect;
import com.mygdx.game.gameEngine.sound.SoundEffectType;
import com.mygdx.game.gameEngine.sound.SoundTrack;
import com.mygdx.game.gameEngine.sound.backgroundMusic;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

public class GameSoundTrack extends SoundTrack {
    private Map<String, backgroundMusic> backgroundMusicMap;
    private Map<String, SoundEffect> soundEffectMap;

//    private Map<SoundEffectType, SoundEffect> soundEffectMap;

    public GameSoundTrack() {
        backgroundMusicMap = new HashMap<>();
        soundEffectMap = new HashMap<>();

        backgroundMusicMap.put("StartingScene", new backgroundMusic("Ground_Theme.wav"));
        backgroundMusicMap.put("GameScene", new backgroundMusic("SkyFire.wav"));

        soundEffectMap.put("StartingScene_Button", new SoundEffect("button_sound.wav"));
        soundEffectMap.put("GameScene_Collision", new SoundEffect("explosion.wav"));
        soundEffectMap.put("eatPowerup", new SoundEffect("eatingSound.wav"));

//        soundEffectMap.put(SoundEffectType.FIRE, new SoundEffect("fire_sound.wav"));
//        soundEffectMap.put(SoundEffectType.TOUCH, new SoundEffect("touch_sound.wav"));
//        soundEffectMap.put(SoundEffectType.BUTTON, new SoundEffect("button_sound.wav"));
//        soundEffectMap.put(SoundEffectType.COLLECT, new SoundEffect("collect_sound.wav"));
    }

    public backgroundMusic getBackgroundMusic(String sceneName) {
        return backgroundMusicMap.get(sceneName);
    }

    public SoundEffect getSoundEffect(String effectName) {
        return soundEffectMap.get(effectName);
    }

    public void pauseAllBackgroundMusic() {
        Collection<backgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
        for (backgroundMusic music : backgroundMusicCollection) {
            music.pause();
        }
    }

    public void resumeAllBackgroundMusic() {
        Collection<backgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
        for (backgroundMusic music : backgroundMusicCollection) {
            music.resume();
        }
    }

    public void stopMusic(String sceneName) {
        backgroundMusic music = getBackgroundMusic(sceneName);
        if (music != null) {
            music.stop();
        } else {
            throw new IllegalArgumentException("Scene name not found in sound map: " + sceneName);
        }
    }

    public void stopAll() {
        Collection<backgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
        for (backgroundMusic music : backgroundMusicCollection) {
            music.stop();
        }
    }

    public void dispose() {
        Collection<backgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
        for (backgroundMusic music : backgroundMusicCollection) {
            music.dispose();
        }

        Collection<SoundEffect> soundEffectCollection = soundEffectMap.values();
        for (SoundEffect sound : soundEffectCollection) {
            sound.dispose();
        }
    }
}


//package com.mygdx.game.gameLayer.sound;
//
//import com.mygdx.game.gameEngine.sound.SoundEffect;
//import com.mygdx.game.gameEngine.sound.SoundEffectType;
//import com.mygdx.game.gameEngine.sound.SoundTrack;
//import com.mygdx.game.gameEngine.sound.backgroundMusic;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public class GameSoundTrack extends SoundTrack {
//    private Map<String, backgroundMusic> backgroundMusicMap;
//    private Map<SoundEffectType, SoundEffect> soundEffectMap;
//
//    public GameSoundTrack() {
//        backgroundMusicMap = new HashMap<>();
//        soundEffectMap = new HashMap<>();
//
//        backgroundMusicMap.put("StartingScene", new backgroundMusic("Ground_Theme.wav"));
//        backgroundMusicMap.put("GameScene", new backgroundMusic("SkyFire.wav"));
//
//        soundEffectMap.put(SoundEffectType.FIRE, new SoundEffect("fire_sound.wav"));
//        soundEffectMap.put(SoundEffectType.TOUCH, new SoundEffect("touch_sound.wav"));
//        soundEffectMap.put(SoundEffectType.BUTTON, new SoundEffect("button_sound.wav"));
//        soundEffectMap.put(SoundEffectType.COLLECT, new SoundEffect("collect_sound.wav"));
//    }
//
//    public backgroundMusic getBackgroundMusic(String sceneName) {
//        return backgroundMusicMap.get(sceneName);
//    }
//
//    public SoundEffect getSoundEffect(SoundEffectType effectType) {
//        return soundEffectMap.get(effectType);
//    }
//
//    public void pauseAllBackgroundMusic() {
//        Collection<backgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
//        for (backgroundMusic music : backgroundMusicCollection) {
//            music.pause();
//        }
//    }
//
//    public void resumeAllBackgroundMusic() {
//        Collection<backgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
//        for (backgroundMusic music : backgroundMusicCollection) {
//            music.resume();
//        }
//    }
//
//    public void dispose() {
//        Collection<backgroundMusic> backgroundMusicCollection = backgroundMusicMap.values();
//        for (backgroundMusic music : backgroundMusicCollection) {
//            music.dispose();
//        }
//
//        Collection<SoundEffect> soundEffectCollection = soundEffectMap.values();
//        for (SoundEffect sound : soundEffectCollection) {
//            sound.dispose();
//        }
//    }
//}