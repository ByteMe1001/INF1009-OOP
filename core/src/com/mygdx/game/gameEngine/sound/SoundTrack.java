package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.gameEngine.sound.SoundEffect;
import com.mygdx.game.gameEngine.sound.backgroundMusic;

public abstract class SoundTrack {

    // Abstract methods for interface with game layer
    public abstract backgroundMusic getBackgroundMusic(String sceneName);
    public abstract SoundEffect getSoundEffect(SoundEffectType soundEffectType);
    public abstract void pauseAllBackgroundMusic();
    public abstract void resumeAllBackgroundMusic();
    public abstract void dispose();
    public abstract void stopAll();
}