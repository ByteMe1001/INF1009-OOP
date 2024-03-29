package com.mygdx.game.gameLayer.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.scene.Scene;
import com.mygdx.game.gameEngine.scene.SceneManager;
import com.mygdx.game.gameEngine.sound.SoundManager;
import com.mygdx.game.gameEngine.util.iIO;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.mygdx.game.gameLayer.entity.EntityFactory;
import com.mygdx.game.gameLayer.player.GamePlayerManager;


public class QuizScene extends Scene implements iIO {

    private EntityManager entityManager;
    private EntityFactory entityFactory;
    private GamePlayerManager gamePlayerManager;
    SceneManager sceneManager = SceneManager.getInstance();

    private final static String TRUE_BUTTON_TEXTURE_PATH = "true_button.png";
    private final static String FALSE_BUTTON_TEXTURE_PATH = "false_button.png";
    private final static String BACKGROUND_TEXTURE_PATH = "quizBG.png";

    private final static float BUTTON_SCALE = 0.05f; // Adjusted scale for buttons
    private Stage stage;
    private BitmapFont font;

    public QuizScene(SceneManager sceneManager, SoundManager soundManager, EntityManager entityManager, SpriteBatch batch) {
    	super(sceneManager, entityManager, soundManager, batch);
        System.out.println(super.getEntityManager());
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();

        // Load background image texture
        Texture backgroundTexture = new Texture(BACKGROUND_TEXTURE_PATH);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundImage.setPosition(0, 0);
        stage.addActor(backgroundImage);

        // Create a table to hold text and buttons
        Table mainTable = new Table();
        mainTable.setFillParent(true); // Table size matches the stage size

        // Add text to the main table
        addCenterText(mainTable, "You should consume at least 5 portions of fruit and vegetables per day.");

        // Create true button
        ImageButton trueButton = createButton(TRUE_BUTTON_TEXTURE_PATH, true);

        // Create false button
        ImageButton falseButton = createButton(FALSE_BUTTON_TEXTURE_PATH, false);

        // Add buttons to the main table
        mainTable.row().padTop(40); // Add a row and padding to separate the text and buttons
        mainTable.add(trueButton).size(150, 50).align(Align.center); // Adjusted button size and alignment
        mainTable.row().pad(10);
        mainTable.add(falseButton).size(150, 50).align(Align.center); // Adjusted button size and alignment

        // Add main table to the stage with debug lines enabled
        stage.addActor(mainTable);

        // Play Screen sound
        if (super.getSoundManager() != null) {
            super.getSoundManager().playMusic("StartingScene");
        } else {
            System.out.println("SoundManager is null in QuizScene");
        }
        
        addButtonClickListener(trueButton, () -> {
            // Logic to execute when the false button is clicked
        	super.getSoundManager().stopMusic("StartingScene");
            super.getSceneManager().swapScene(new CongratsScene(QuizScene.super.getSceneManager(), QuizScene.super.getEntityManager(), QuizScene.super.getSoundManager(), getBatch()));
            //super.getSceneManager().swapScene(new GameScene(StartingScene.super.getSceneManager(), StartingScene.super.getSoundManager(), getBatch()));
        });
        
        addButtonClickListener(falseButton, () -> {
            // Logic to execute when the false button is clicked
        	super.getSoundManager().stopMusic("StartingScene");
            super.getSceneManager().swapScene(new exitScene(QuizScene.super.getSceneManager(), QuizScene.super.getEntityManager(), QuizScene.super.getSoundManager(), getBatch()));
            //super.getSceneManager().swapScene(new GameScene(StartingScene.super.getSceneManager(), StartingScene.super.getSoundManager(), getBatch()));
        });
    }

    private ImageButton createButton(String buttonTexturePath, final boolean answer) {
        // Load button texture
        Texture buttonTexture = new Texture(buttonTexturePath);
        TextureRegion buttonRegion = new TextureRegion(buttonTexture);
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonRegion);

        // Create button
        ImageButton button = new ImageButton(buttonDrawable);

        // Set the size of the button
        button.setSize(buttonTexture.getWidth() * BUTTON_SCALE, buttonTexture.getHeight() * BUTTON_SCALE);

        return button;
    }


   /* private void handleFalseButtonClick() {

    	SceneManager sceneManager = getSceneManager();
        sceneManager.createExitScene();
        sceneManager.swapScene(sceneManager.getExitScene());

    } */

    private void addCenterText(Table table, String text) {
        // Create LabelStyle with the initialized font
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        // Set font color
        labelStyle.fontColor = Color.BLACK;

        // Create Label with the provided text and style
        Label label = new Label(text, labelStyle);

        // Set font scale to increase the font size
        float scale = 2.0f; // Adjust the scale factor as needed
        label.setFontScale(scale);

        // Set label alignment to center-left
        label.setAlignment(com.badlogic.gdx.utils.Align.left);

        // Set wrap to true to wrap text based on the size of the label
        label.setWrap(true);

        // Add the label to the table and set its width
        table.add(label).width(Gdx.graphics.getWidth() * 0.8f).padLeft(25).padBottom(10); // Add label with bottom padding and left padding
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
        QuizScene.super.getSoundManager().stopAll(); // Stop music only, do not dispose sound manager
        stage.dispose(); // Dispose stage
    }
}
