package com.mygdx.game.scene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StartingScene extends Scene {

    private final static String TEXTURE_PATH = "playbutton.png";
    private Stage stage;
    private SceneManager sceneManager;

    public StartingScene(SceneManager sceneManager, SpriteBatch batch) {
        super(sceneManager, batch);
        this.sceneManager = sceneManager;
        create();
    }


    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Load play button texture
        Texture playButtonTexture = new Texture(TEXTURE_PATH);
        TextureRegion playButtonRegion = new TextureRegion(playButtonTexture);
        TextureRegionDrawable playButtonDrawable = new TextureRegionDrawable(playButtonRegion);

        // Create play button
        ImageButton playButton = new ImageButton(playButtonDrawable);

        // Set the size of the play button
        float buttonWidth = 200;
        float buttonHeight = 100;

        // Calculate button position to center it on the screen
        float buttonX = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        float buttonY = (Gdx.graphics.getHeight() - buttonHeight) / 2;

        playButton.setSize(buttonWidth, buttonHeight);
        playButton.setPosition(buttonX, buttonY);

        // Add play button to the stage
        stage.addActor(playButton);

        // Add click listener to the play button
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Log statement to verify the click listener
                Gdx.app.log("StartingScene", "Play button clicked");
                // Swap to the game scene when the play button is clicked
                sceneManager.swapScene(new GameScene(sceneManager, getBatch()));
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
        stage.dispose(); // Dispose stage
    }
}
