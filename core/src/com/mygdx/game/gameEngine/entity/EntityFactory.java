package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameLayer.entity.Boy;
import com.mygdx.game.gameLayer.entity.Boss;
import com.mygdx.game.gameLayer.entity.EntityType;

public class EntityFactory implements GameFactory {
    private EntityType entityType = EntityType.CHARACTER;

    private SpriteBatch batch;

    public EntityFactory(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityFactory(SpriteBatch batch) {
        EntityType entityType = EntityType.CHARACTER;
        this.batch = batch;
    }

    @Override
    public DisplayElement createDisplayElement() {
        // EntityFactory does not create UI elements, return null or throw an exception
        return null;
    }

    @Override
    public Entity createEntity() {
        switch (entityType) {
            case CHARACTER:
                return createCharacterEntity();
            case BOSS:
                return createBossEntity();
            // Add cases for other entity types as needed
            default:
                throw new IllegalArgumentException("Invalid entity type: " + entityType);
        }
    }

    private Entity createCharacterEntity() {
        return new Boy(
                entityType.id, entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)), entityType.width, entityType.height,
                entityType.speed, entityType.direction, null, entityType.isAlive,
                entityType.isCollidable, batch
        );
    }

    private Entity createBossEntity() {
        return new Boss(
                entityType.id, entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)), entityType.width, entityType.height,
                entityType.speed, entityType.direction, null, entityType.isAlive,
                entityType.isCollidable
        );
    }

}
