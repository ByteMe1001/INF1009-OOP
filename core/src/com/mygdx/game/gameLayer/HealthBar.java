package com.mygdx.game.gameLayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gameEngine.player.PlayerControlManager;
import com.mygdx.game.gameEngine.util.iIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthBar implements iIO {

    private PlayerControlManager playerControlManager;
    private BitmapFont font;

    private Texture blank, green;


    protected static final float BAR_WIDTH = (float) Gdx.graphics.getWidth() / 2;
    protected static final float BAR_HEIGHT = 15f;

    public HealthBar(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
        // TEXTURES
        blank = new Texture("blackbackground.PNG");
        green = new Texture("green.jpg");
    }

    public void draw(SpriteBatch batch) {
        float healthBarX = 10;
        float healthBarY = Gdx.graphics.getHeight() - 30;
        HashMap<Integer, List<Integer>> healthDataMap = playerControlManager.getHealthDataMap();

        for (int i = 0; i < healthDataMap.size(); i++) {
            List<Integer> healthData = healthDataMap.get(i);
            int health = healthData.get(0);
            int maxHealth = healthData.get(1);

            if (i != 0) {
                healthBarY -= BAR_HEIGHT + 50;
            }

            //For HealthBar logic
            batch.draw(blank, healthBarX, healthBarY, BAR_WIDTH, BAR_HEIGHT);
            // Draw foreground health bar based on current health
            float foregroundWidth = BAR_WIDTH * ((float)health / maxHealth);           // Calculate width based on current health percentage
            batch.draw(green, healthBarX, healthBarY, foregroundWidth, BAR_HEIGHT);
            //batch.draw(blank,0, 0, Gdx.graphics.getWidth() * health, 5);
        }
    }
}
