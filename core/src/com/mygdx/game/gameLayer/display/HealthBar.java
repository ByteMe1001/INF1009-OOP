package com.mygdx.game.gameLayer.display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameLayer.player.GamePlayerManager;
import com.mygdx.game.gameEngine.util.iIO;

import java.util.HashMap;
import java.util.List;

public class HealthBar implements iIO {

    private GamePlayerManager gamePlayerManager;
    private Texture blank, green;

    private static final float BAR_WIDTH = (float) Gdx.graphics.getWidth() / 2;
    private static final float BAR_HEIGHT = 15f;

    // Constructor to initialize health bar
    public HealthBar(GamePlayerManager gamePlayerManager) {
        this.gamePlayerManager = gamePlayerManager;
        // TEXTURES
        blank = new Texture("blackbackground.PNG");
        green = new Texture("green.jpg");
    }

    // Draw & update health bar
    public void draw(SpriteBatch batch) {
        float healthBarX = 10;
        float healthBarY = Gdx.graphics.getHeight() - 30;
        HashMap<Integer, List<Integer>> healthDataMap = gamePlayerManager.getHealthDataMap();

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
