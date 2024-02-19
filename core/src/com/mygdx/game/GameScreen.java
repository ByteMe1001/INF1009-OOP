package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    // Screen variables
    private Camera camera;
    private Viewport viewport;

    //Graphics variables
    protected SpriteBatch batch;
    private Texture background;

    //Timing
    private int backgroundOffset;

    //World
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    public GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        background = new Texture("Space.jpg");
        backgroundOffset = 0;

        batch = new SpriteBatch();
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        batch.begin();

        //Scroll
        backgroundOffset++;

        if (backgroundOffset % WORLD_HEIGHT == 0){
            backgroundOffset = 0;
        }

        batch.draw(background, 0, -backgroundOffset, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(background, 0, -backgroundOffset+WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        if (this != null) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}