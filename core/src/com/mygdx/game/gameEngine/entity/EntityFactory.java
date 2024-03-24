package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameLayer.entity.*;

public class EntityFactory implements GameFactory {
    private EntityType entityType;
    private EntityManager entityManager;

    private SpriteBatch batch;

    public EntityFactory(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityFactory(SpriteBatch batch) {
        //EntityType entityType = EntityType.BOY;
        this.batch = batch;
    }

    public EntityFactory(SpriteBatch batch, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.batch = batch;
    }

    @Override
    public DisplayElement createDisplayElement() {
        // EntityFactory does not create UI elements, return null or throw an exception
        return null;
    }

    @Override
    public Entity createEntity(EntityType entityType) {
        switch (entityType) {
            case BOY:
                return createCharacterEntity();
            case BOSS:
                return createBossEntity();
            case BULLET:
                return createBulletEntity();
            // Add cases for other entity types as needed
            case ENEMYBULLET:
                return createEnemyBullet();
            default:
                throw new IllegalArgumentException("Invalid entity type: " + entityType);
        }
    }

    public Entity createCharacterEntity() {
        EntityType entityType = EntityType.BOY;
        return new Boy(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.playerMovement,batch,this,entityManager
        );
    }


    public Entity createBossEntity() {
        EntityType entityType = EntityType.BOSS;
        return new Boss(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch
        );
    }

    public Entity createBulletEntity() {
        EntityType entityType = EntityType.BULLET;
        return new Bullet(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch
        );
    }

    public Entity createEnemyBullet() {
        EntityType entityType = EntityType.ENEMYBULLET;
        return new EnemyBullet(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch
        );
    }
//For shoot function
    public Entity shootBullets(float x ,float y) {
        EntityType entityType = EntityType.BULLET;
        return new Bullet(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, batch
        );
    }
}
/*public void creatX(float x ,float y) {
        EntityType entityType = EntityType.BULLET;
         Bullet bullet = new Bullet(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, batch
        );
        EntityManager.addEntity(Entity(bullet))
    }
    */
