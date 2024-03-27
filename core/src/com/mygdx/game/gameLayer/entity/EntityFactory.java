package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.entity.DisplayElement;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.entity.GameFactory;
import com.mygdx.game.gameLayer.entity.*;

public class EntityFactory implements GameFactory {
    private EntityType entityType;
    private EntityManager entityManager;

    private SpriteBatch batch;

    public EntityFactory(EntityType entityType) {
        this.entityType = entityType;
    }

//    public EntityFactory(SpriteBatch batch) {
//        //EntityType entityType = EntityType.BOY;
//        this.batch = batch;
//    }

    public EntityFactory(SpriteBatch batch, EntityManager entityManager) {
        this.entityManager = entityManager;
        System.out.println(entityManager);
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
        Boy entity = new Boy(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.playerMovement,batch,this, entityManager
        );

        // Add the created entity to the EntityManager
        entityManager.addEntity(entity);

        return entity;
    }


    public Entity createBossEntity() {
        EntityType entityType = EntityType.BOSS;
        Boss entity = new Boss(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch, this, entityManager
        );

        // Add the created entity to the EntityManager
        entityManager.addEntity(entity);

        return entity;
    }

    public Entity createBulletEntity() {
        EntityType entityType = EntityType.BULLET;
        Bullet entity = new Bullet(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch
        );

        // Add the created entity to the EntityManager
        entityManager.addEntity(entity);

        return entity;
    }

    public Entity createEnemyBullet() {
        EntityType entityType = EntityType.ENEMYBULLET;
        EnemyBullet entity = new EnemyBullet(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch
        );

        // Add the created entity to the EntityManager
        entityManager.addEntity(entity);

        return entity;
    }

    //For shoot function
    public Entity shootBullets(float x ,float y) {
        EntityType entityType = EntityType.BULLET;
        Bullet bullet = new Bullet(
                entityType.health, x, y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch
        );

        // Add the created bullet entity to the EntityManager
        entityManager.addEntity(bullet);

        return bullet;
    }
    
    public Entity shootEnemyBullet(float x, float y) {
        EntityType entityType = EntityType.ENEMYBULLET;
        EnemyBullet enemyBullet = new EnemyBullet(
                entityType.health, x, y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch
        );

        // Add the created enemy bullet entity to the EntityManager
        entityManager.addEntity(enemyBullet);

        return enemyBullet;
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
