package com.mygdx.game.gameLayer.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
import com.mygdx.game.gameLayer.player.GamePlayerManager;
import com.mygdx.game.gameLayer.display.HealthBar;
import com.mygdx.game.gameLayer.entity.EntityFactory;
import com.mygdx.game.gameEngine.scene.Scene;
import com.mygdx.game.gameEngine.scene.SceneManager;
import com.mygdx.game.gameEngine.sound.SoundManager;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.gameEngine.util.iIO;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class BossScene extends Scene implements iIO {
    private final static String TEXTURE_PATH = "boss_BG.png";
    private final static String PAUSE_BUTTON_PATH = "pause_button.png";
    private final static String RESUME_BUTTON_PATH = "resume_button.png";
    private Stage stage; // Stage for buttons
    private Texture pauseButtonTexture;
    private Texture resumeButtonTexture;
    private Texture homeButtonTexture;
    private boolean isPaused = false;
    private SoundManager soundManager;
    //private SceneManager sceneManager;
    private float backgroundY = 0;
    private float backgroundVelocity = 9;

    // HealthBar Demo Code
    private HealthBar healthBar;



    private int health = 100;
    private Texture blank, green;
    private CollidableEntities boss;
    private float healthBarWidth = 200f; // Width of the health bar
    private float healthBarHeight = 20f; // Height of the health bar

    private float elapsedTime = 0f;
    private float healthDecreaseInterval = 3f;
    private int healthDecreaseAmount = 10;

    private EntityManager entityManager;
    private EntityFactory entityFactory;

    private GamePlayerManager gamePlayerManager;
    SceneManager sceneManager = SceneManager.getInstance();



    public BossScene(SceneManager sceneManager, EntityManager entityManager, SoundManager soundManager, SpriteBatch batch) {
        super(sceneManager, entityManager, soundManager, batch);
        this.gamePlayerManager = (GamePlayerManager) (entityManager.getAbstractGamePlayerManager());
        this.entityFactory = new EntityFactory(batch, entityManager); // Assign the provided EntityFactory instance
        this.sceneManager = sceneManager;
        this.soundManager = soundManager;
    }

    @Override
    public void show() {
        super.setBackground(new Texture(TEXTURE_PATH));
        super.show();

        // Initialize stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Load pause button texture
        pauseButtonTexture = new Texture(PAUSE_BUTTON_PATH);
        resumeButtonTexture = new Texture(RESUME_BUTTON_PATH);

        //Making HealthBar
        blank = new Texture("blackbackground.PNG");
        green = new Texture("green.jpg");

        // Create UI table
        Table uiTable = new Table();
        //uiTable.setDebug(true); // Enable debug lines, to remove when button and positions are confirmed
        uiTable.setFillParent(true); // Table fills the entire stage
        uiTable.top().right(); // Align the table to the top right corner of the stage

        // Create pause button
        ImageButton pauseButton = createPauseButton();
        ImageButton homeButton = createHomeButton();


        // button position
        float buttonWidth = 100f; // Specify the width of the button
        float buttonHeight = 35f; // Specify the height of the button
        uiTable.add(pauseButton).width(buttonWidth).height(buttonHeight).padRight(10).align(Align.top).align(Align.right).padTop(20);
        //uiTable.add(resumeButton).width(buttonWidth).height(buttonHeight).padRight(20).align(Align.top).align(Align.right).padTop(20);
        uiTable.add(homeButton).width(buttonWidth).height(buttonHeight).padRight(20).align(Align.top).align(Align.right).padTop(20);

        // Add UI table to the stage
        stage.addActor(uiTable);

        // Start playing music if not already playing
        if (!super.getSoundManager().isMusicPlaying()) {
            super.getSoundManager().playMusic("GameScene");
        }
        //Get EntityFactory.createEntity(id,x,y,z)

        // Create entities TO CHANGE ACCODRING TO BOSS LEVEL
        // BOY = 0, ENEMY = 1, BOSS = 2, BULLET = 3, ENEMYBULLET = 4, HEALTHPACK = 5
        entityFactory.createEntity(0,1);        // Create player
        entityFactory.createEntity(2,1);        // Create boss

        // HealthBar Demo Code
        this.healthBar = new HealthBar(gamePlayerManager);
    }



    private ImageButton createPauseButton() {
        // Create pause button
        TextureRegion pauseButtonRegion = new TextureRegion(pauseButtonTexture);
        TextureRegionDrawable pauseButtonDrawable = new TextureRegionDrawable(pauseButtonRegion);
        ImageButton pauseButton = new ImageButton(pauseButtonDrawable);

        //create resume button
        // Create paused button texture
        Texture pausedButtonTexture = new Texture(RESUME_BUTTON_PATH);
        TextureRegion pausedButtonRegion = new TextureRegion(pausedButtonTexture);
        TextureRegionDrawable resumeButtonDrawable = new TextureRegionDrawable(pausedButtonRegion);

        //resume button
        ImageButton resumeButton = new ImageButton(pauseButtonDrawable);

        // Align the button within its cell to top-center
        pauseButton.getImageCell().align(Align.center);

        // Add click listener to the pause button
        addButtonClickListener(pauseButton, () -> {
            // Pause button logic
            SceneManager sceneManager = getSceneManager();
            if (sceneManager.isPaused()) {
                sceneManager.resume();
                pauseButton.getStyle().imageUp = pauseButtonDrawable;
            } else {
                sceneManager.pause();
                pauseButton.getStyle().imageUp = resumeButtonDrawable;
            }
        });

        return pauseButton;
    }


    private ImageButton createHomeButton() {
        TextureRegion backBtnRegion = new TextureRegion(new Texture("restart_button.png"));
        TextureRegionDrawable backBtnDrawable = new TextureRegionDrawable(backBtnRegion);
        ImageButton backButton = new ImageButton(backBtnDrawable);

        addButtonClickListener(backButton, () -> {
            // Back button logic
            // Stop and dispose of the music
            getSoundManager().stopAll();
            getSoundManager().dispose();

            // Clear entities
            getEntityManager().dispose();

            // Navigate to the StartingScene
            sceneManager.createStartingScene();
            sceneManager.swapScene(sceneManager.getStartingScene());
        });

        return backButton;
    }


    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1); // Clear screen
        super.getBatch().begin();
        //For moving scene
        super.getBatch().draw(super.getBackground(), 0, backgroundY, 640, 640);
        super.getBatch().draw(super.getBackground(), 0, backgroundY + 640, 640, 640);

        // Game Loop
        // PATCH FIX
        if (!getSceneManager().isPaused()) {
            super.getEntityManager().update();
            healthBar.draw(getBatch());

            // Check if all boss entities are dead and go to the QuizScene
            if (super.getEntityManager().areAllEnemiesDead()) {
                SceneManager sceneManager = getSceneManager();
                SoundManager soundManager = getSoundManager();
                SpriteBatch batch = getBatch();
                //batch.end();
                sceneManager.swapScene(new QuizScene(sceneManager, soundManager, entityManager, batch));
                return; // Skip the remaining rendering code since we're going to a new scene
            }

            //Below here is the logic for the moving scene when not paused
            backgroundY -= backgroundVelocity;

            if (backgroundY +640 <=0){
                backgroundY += 640;
            }
        }

        super.getEntityManager().draw();
        super.getBatch().end();


        // Draw stage
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void dispose() {
        // Dispose of resources
        super.dispose();
        pauseButtonTexture.dispose();
        stage.dispose();
    }

    @Override
    public void pause() {
        // Pause the game
        super.pause();
    }

    @Override
    public void resume() {
        // Resume the game
        super.resume();
    }

    @Override
    public void hide() {
        // Hide logic
    }
}





