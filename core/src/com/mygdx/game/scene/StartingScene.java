package com.mygdx.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.util.SoundManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class StartingScene extends Scene {

    private final static String TEXTURE_PATH = "playbutton.png";
    private final static String BACKGROUND_TEXTURE_PATH = "StartingImage.png";

    private final static float BUTTON_SCALE = 0.2f;         // Scale to set button
    private Stage stage;

    protected StartingScene(SceneManager sceneManager, SoundManager soundManager, SpriteBatch batch) {
        super(sceneManager, soundManager, batch);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Load background image texture
        Texture backgroundTexture = new Texture(BACKGROUND_TEXTURE_PATH);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundImage.setPosition(0, 0);
        stage.addActor(backgroundImage);

        // Load playButton texture
        Texture playButtonTexture = new Texture(TEXTURE_PATH);
        TextureRegion playButtonRegion = new TextureRegion(playButtonTexture);
        TextureRegionDrawable playButtonDrawable = new TextureRegionDrawable(playButtonRegion);

        // Create play button
        ImageButton playButton = new ImageButton(playButtonDrawable);

        // Set the size of the play button
        float buttonWidth = playButtonTexture.getWidth() * BUTTON_SCALE;
        float buttonHeight = playButtonTexture.getHeight() * BUTTON_SCALE;

        // Calculate button position to center it on the screen
        float buttonX = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        float buttonY = (Gdx.graphics.getHeight() - buttonHeight) / 2;

        playButton.setSize(buttonWidth, buttonHeight);
        playButton.setPosition(buttonX, buttonY);

        // Add play button to the stage
        stage.addActor(playButton);

        // Play sound
        playButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Play music when cursor enters the play button area
                StartingScene.super.getSoundManager().playMusic("StartingScene_Button");
            }
        });
        super.getSoundManager().playMusic("StartingScene");

        // Add click listener to the play button
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Notify the SceneManager to swap the scene when the play button is clicked
                StartingScene.super.getSceneManager().swapScene(new GameScene(StartingScene.super.getSceneManager(), StartingScene.super.getSoundManager(), getBatch()));
            }
        });
    }

    @Override
    public void render(float deltaTime) {
        // Clear the screen
        ScreenUtils.clear(0, 0, 0, 1);

        // Draw stage
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void dispose() {
        StartingScene.super.getSoundManager().stopAll();        // Stop music only, do not dispose sound manager
        stage.dispose(); // Dispose stage
    }
}
