package com.mygdx.game.gameEngine.sound;

public abstract class SoundTrack {

    // Abstract methods for interface with game layer
    public abstract BackgroundMusic getBackgroundMusic(String sceneName);
    public abstract SoundEffect getSoundEffect(SoundEffectType soundEffectType);
    public abstract void pauseAllBackgroundMusic();
    public abstract void resumeAllBackgroundMusic();
    public abstract void dispose();
    public abstract void stopAll();
}