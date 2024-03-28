package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.entity.DisplayElement;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.entity.Factory;

import java.util.HashMap;

public class EntityFactory implements Factory {
    private EntityType entityType;

    private HashMap<Integer, EntityType> entityTypeMap;
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
        this.batch = batch;

        this.entityTypeMap = new HashMap<>();
        for (EntityType entityType : EntityType.values()) {
            this.entityTypeMap.put(entityType.ordinal(), entityType);
        }
    }

    @Override
    public DisplayElement createDisplayElement() {
        // EntityFactory does not create UI elements, return null or throw an exception
        return null;
    }

    @Override
    public Entity createEntity(int entityTypeKey, int quantity) {
        EntityType entityType = entityTypeMap.get(entityTypeKey);
        System.out.println("Entity Type: " + entityType);
        if (entityType == null) {
            throw new IllegalArgumentException("Invalid entity type key: " + entityTypeKey);
        }
        switch (entityType) {
            case BOY:
                return createCharacterEntity();
            case ENEMY:
                return createEnemyEntity();
            case BOSS:
                return createBossEntity();
            case BULLET:
                return createBulletEntity();
            case ENEMYBULLET:
                return createEnemyBullet();
            case HEALTHPACK:
                return createHealthPack();
            default:
                throw new IllegalArgumentException("Invalid entity type: " + entityType);
        }
    }

    public Entity createCharacterEntity() {
        EntityType entityType = EntityType.BOY;
        Boy entity = new Boy(
                entityType.health, entityType.maxHealth, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.playerMovement,batch,this, entityManager
        );

        // Add the created entity to the EntityManager
        entityManager.addEntity(entity);

        return entity;
    }

    public Entity createEnemyEntity() {
        EntityType entityType = EntityType.ENEMY;
        Enemy entity = new Enemy(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch, this, entityManager
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

    public Entity createHealthPack() {
        EntityType entityType = EntityType.HEALTHPACK;
        HealthPack healthPack = new HealthPack(
                entityType.health, entityType.x, entityType.y, entityType.scale,
                new Sprite(new Texture(entityType.texturePath)),
                entityType.speed, entityType.defaultChangeRate, batch
        );

        // Add the created enemy bullet entity to the EntityManager
        entityManager.addEntity(healthPack);

        return healthPack;
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
