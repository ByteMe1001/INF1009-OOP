package com.mygdx.game.gameLayer.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.gameEngine.entity.CollidableEntities;
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

public class GameScene extends Scene implements iIO {
    private final static String TEXTURE_PATH = "scrolling_Background.png";
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
    private float backgroundVelocity = 4;

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
    SceneManager sceneManager = SceneManager.getInstance();



    public GameScene(SceneManager sceneManager, EntityManager entityManager, SoundManager soundManager, SpriteBatch batch) {
        super(sceneManager, entityManager, soundManager, batch);
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

        // Create entities
        // BOY = 0, ENEMY = 1, BOSS = 2, BULLET = 3, ENEMYBULLET = 4, HEALTHPACK = 5
        entityFactory.createEntity(0,1);        // Create player
        entityFactory.createEntity(1,3);        // Create enemy
        entityFactory.createEntity(2,1);        // Create boss
        entityFactory.createEntity(5,3);        // Create health pack
//        super.getEntityManager().createEnemies(5);
//        super.getEntityManager().createPlayerBullets(0);
//        super.getEntityManager().createEnemyBullets(1);
//        super.getEntityManager().createHealthPack(1);
//        super.getEntityManager().createBoss();
        //pause button logic

        // HealthBar Demo Code
        this.healthBar = new HealthBar(super.getEntityManager().getPlayerControlManager());

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


        return pauseButton;
    }


    //Replace with a method to update the entity health
//    private void decreaseHealth() {
//        health -= healthDecreaseAmount;
//        if (health < 0) {
//            health = 0;
//        }
//    }



    private ImageButton createHomeButton() {
        TextureRegion backBtnRegion = new TextureRegion(new Texture("restart_button.png"));
        TextureRegionDrawable backBtnDrawable = new TextureRegionDrawable(backBtnRegion);
        ImageButton backButton = new ImageButton(backBtnDrawable);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	// Stop and dispose of the music
                getSoundManager().stopAll();
                getSoundManager().dispose();

                // Clear entities
                getEntityManager().dispose();

                // Navigate to the StartingScene
                sceneManager.createStartingScene(); 
                sceneManager.swapScene(sceneManager.getStartingScene());
            }
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




//        //For HealthBar logic
//        super.getBatch().draw(blank, 10, Gdx.graphics.getHeight() - 30, Gdx.graphics.getWidth()/2, Gdx.graphics.getWidth()/2);
//        // Draw foreground health bar based on current health
//        float foregroundWidth = Gdx.graphics.getWidth()/2 * (health / 100.0f); // Calculate width based on current health percentage
//        super.getBatch().draw(green, 10, Gdx.graphics.getHeight() - 30, foregroundWidth, healthBarHeight);
//        super.getBatch().draw(blank,0, 0, Gdx.graphics.getWidth() * health, 5);




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
                sceneManager.swapScene(new QuizScene(sceneManager, soundManager, entityManager, batch));
                return; // Skip the remaining rendering code since we're going to a new scene
            }

            

            //Below here is the logic for the moving scene when not paused
            backgroundY -= backgroundVelocity;

            if (backgroundY +640 ==0){
                backgroundY = 0;
            }
        }



//        //Here is just a timer for the healthbar to show it deducting, remove once logic in implemented
//        elapsedTime += deltaTime;
//        if (elapsedTime >= healthDecreaseInterval) {
//            decreaseHealth();
//            elapsedTime = 0f;
//        }


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
