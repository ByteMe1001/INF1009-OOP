package com.mygdx.game.gameLayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gameEngine.player.PlayerControlManager;
import com.mygdx.game.gameEngine.util.iIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthBar implements iIO {

    private PlayerControlManager playerControlManager;
    private BitmapFont font;

    private ShapeRenderer shapeRenderer;

    protected static final float BAR_WIDTH = (float) Gdx.graphics.getWidth() / 2;
    protected static final float BAR_HEIGHT = 15;



    public HealthBar(PlayerControlManager playerControlManager, ShapeRenderer shapeRenderer) {
        this.playerControlManager = playerControlManager;
        this.shapeRenderer = shapeRenderer;
        this.font = new BitmapFont();
    }

    public void draw() {
        float healthBarX = 10;
        float healthBarY = Gdx.graphics.getHeight() - 10;

        HashMap<Integer, List<Integer>> healthDataMap = playerControlManager.getHealthDataMap();

        for (int i = 0; i < healthDataMap.size(); i++) {
            List<Integer> healthData = healthDataMap.get(i);
            int health = healthData.get(0);
            int maxHealth = healthData.get(1);

            if (i != 0) {
                healthBarY -= BAR_HEIGHT + 50;
            }
            drawShape(shapeRenderer, "RECTANGLE", healthBarX, healthBarY, BAR_WIDTH, BAR_HEIGHT, Color.BLACK);
            drawShape(shapeRenderer, "RECTANGLE", healthBarX, healthBarY, (BAR_WIDTH * health / maxHealth), BAR_HEIGHT, Color.GREEN);
        }
        shapeRenderer.end();
    }

}
