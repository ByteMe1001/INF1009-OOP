package com.mygdx.game.gameLayer.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.gameLayer.entity.EntityFactory;
import com.mygdx.game.gameEngine.scene.Scene;
import com.mygdx.game.gameEngine.scene.SceneManager;
import com.mygdx.game.gameEngine.util.SoundManager;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.gameEngine.util.iIO;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class GameScene extends Scene implements iIO {
    private final static String TEXTURE_PATH = "scrolling_Background.png";
    private final static String PAUSE_BUTTON_PATH = "pause_button.png";
    private final static String RESUME_BUTTON_PATH = "resume_button.png";
    private Stage stage; // Stage for buttons
    private Texture pauseButtonTexture;
    private Texture resumeButtonTexture;
    private boolean isPaused = false;
    private SoundManager soundManager;
    private SceneManager sceneManager;
    private float backgroundY = 0;
    private float backgroundVelocity = 4;
    private EntityManager entityManager;
    private EntityFactory entityFactory;



    public GameScene(SceneManager sceneManager, EntityManager entityManager, SoundManager soundManager, SpriteBatch batch) {
        super(sceneManager, entityManager, soundManager, batch);
        this.entityFactory = new EntityFactory(batch, entityManager); // Assign the provided EntityFactory instance
        this.sceneManager = sceneManager;
        this.soundManager = soundManager;
    }

    @Override
    public void show() {
        super.setBackground(new Texture(TEXTURE_PATH));

        // Initialize stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Load pause button texture
        pauseButtonTexture = new Texture(PAUSE_BUTTON_PATH);
        resumeButtonTexture = new Texture(RESUME_BUTTON_PATH);

        // Create UI table
        Table uiTable = new Table();
        //uiTable.setDebug(true); // Enable debug lines, to remove when button and positions are confirmed
        uiTable.setFillParent(true); // Table fills the entire stage
        uiTable.top().right(); // Align the table to the top right corner of the stage

        // Create pause button
        ImageButton pauseButton = createPauseButton();


        // button position
        float buttonWidth = 100f; // Specify the width of the button
        float buttonHeight = 35f; // Specify the height of the button
        uiTable.add(pauseButton).width(buttonWidth).height(buttonHeight).padRight(10).align(Align.top).align(Align.right).padTop(20);
        //uiTable.add(resumeButton).width(buttonWidth).height(buttonHeight).padRight(20).align(Align.top).align(Align.right).padTop(20);

        // Add UI table to the stage
        stage.addActor(uiTable);

        // Start playing music if not already playing
        if (!super.getSoundManager().isMusicPlaying()) {
            super.getSoundManager().playMusic("GameScene");
        }
        //Get EntityFactory.createEntity(id,x,y,z)
        // Create entities
        super.getEntityManager().createBucket();
        super.getEntityManager().createDroplets(5);
        super.getEntityManager().createPlayerBullets(1);
        super.getEntityManager().createEnemyBullets(1);
        //pause button logic

    }

    private void resumeGame() {

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
        //pause button logic
        // Pause button logic
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                SceneManager sceneManager = getSceneManager();
                if (sceneManager.isPaused()) {
                    sceneManager.resume();
                    pauseButton.getStyle().imageUp = pauseButtonDrawable;
                } else {
                    sceneManager.pause();
                    pauseButton.getStyle().imageUp = resumeButtonDrawable;
                }
            }
        });

//                // If the game is currently paused, resume it; otherwise, pause it
//                if (isPaused) {
//                    resume();
//                    pauseButton.getStyle().imageUp = pauseButtonDrawable;
//                } else {
//                    pause();
//                    pauseButton.getStyle().imageUp = resumeButtonDrawable;
//                }
//
//            }
//        });


        return pauseButton;
    }

//    private ImageButton createResumeButton() {
//        // Create resume button
//        TextureRegion resumeButtonRegion = new TextureRegion(resumeButtonTexture);
//        TextureRegionDrawable resumeButtonDrawable = new TextureRegionDrawable(resumeButtonRegion);
//        ImageButton resumeButton = new ImageButton(resumeButtonDrawable);
//
//        // Align the button within its cell to top-center
//        resumeButton.getImageCell().align(Align.center);
//
//        // Add click listener to the resume button
//        resumeButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                // Handle resume button click event
//                resumeGame();
//            }
//        });
//
//        return resumeButton;
//    }


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

            //Below here is the logic for the moving scene when not paused
            backgroundY -= backgroundVelocity;

            if (backgroundY +640 ==0){
                backgroundY = 0;
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
//        isPaused = true; // Set the isPaused flag to true
//        System.out.println("Game Paused"); // Print a message indicating that the game is paused
//
//        // Pause sound effects and music
//        soundManager.pauseAll();

        // Stop entity movements
        //super.getEntityManager().stopAllMovements(); // Implement this method in your EntityManager class


    }

    @Override
    public void resume() {
        // Resume the game
        super.resume();
//        isPaused = false; // Set the isPaused flag to false
//        System.out.println("Game Resumed"); // Print a message indicating that the game is resumed
//
//        // Resume sound effects and music
//        soundManager.resumeAll();

        // Resume any other game activities
    }



    @Override
    public void hide() {
        // Hide logic
    }

}





// ORIGINAL CODES
//public class GameScene extends Scene implements iIO {
//
//
//    private final static String TEXTURE_PATH = "Space.jpg";
//
//    public GameScene(SceneManager sceneManager, EntityManager entityManager, SoundManager soundManager, SpriteBatch batch) {
//        super(sceneManager,entityManager, soundManager, batch);
//        //this.entityManager = new EntityManager(soundManager, batch);        // Can put here or show()
//    }
//
//    @Override
//    public void show() {
//        super.setBackground(new Texture(TEXTURE_PATH));
//        if (!super.getSoundManager().isMusicPlaying()) {
//            super.getSoundManager().playMusic("GameScene");
//        }
//        super.getEntityManager().createBucket();
//        super.getEntityManager().createDroplets(5);
//    }
//
//    @Override
//    public void render(float deltaTime) {
//        ScreenUtils.clear(0, 0, 0, 1);      // Clear screen
//
//        // Render logic here
//        super.getBatch().begin();
//        // Background code
//        super.getBatch().draw(super.getBackground(), 0, 0, 640,640);
//        // Entity Manager codes
//        super.getEntityManager().update();
//        super.getEntityManager().draw();
//        super.getBatch().end();
//    }
//
//    @Override
//    public void dispose() {
//        // Dispose of resources
//        super.getEntityManager().dispose();
//        super.getSoundManager().stopAll();
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//
//}
