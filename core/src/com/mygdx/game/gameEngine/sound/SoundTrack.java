package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.gameEngine.sound.SoundEffect;
import com.mygdx.game.gameEngine.sound.backgroundMusic;

public abstract class SoundTrack {
    public abstract backgroundMusic getBackgroundMusic(String sceneName);
    public abstract SoundEffect getSoundEffect(String effectName);
    public abstract void pauseAllBackgroundMusic();
    public abstract void resumeAllBackgroundMusic();
    public abstract void dispose();
}