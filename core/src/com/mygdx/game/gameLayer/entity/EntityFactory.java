package com.mygdx.game.gameLayer.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public EntityFactory(SpriteBatch batch, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.batch = batch;

        this.entityTypeMap = new HashMap<>();
        for (EntityType entityType : EntityType.values()) {
            this.entityTypeMap.put(entityType.ordinal(), entityType);
        }
    }

    // TODO: change return type? void looks ok?
    @Override
    public Entity createEntity(int entityTypeKey, int quantity) {
        EntityType entityType = entityTypeMap.get(entityTypeKey);
        System.out.println("Entity Type: " + entityType);
        if (entityType == null) {
            throw new IllegalArgumentException("Invalid entity type key: " + entityTypeKey);
        }
        Entity entity = null;
        for (int i = 0; i < quantity; i++) {
            switch (entityType) {
                case BOY:
                    entity = createCharacterEntity();
                    break;
                case ENEMY:
                    entity = createEnemyEntity();
                    break;
                case BOSS:
                    entity = createBossEntity();
                    break;
                case BULLET:
                    entity = createBulletEntity();
                    break;
                case ENEMYBULLET:
                    entity = createEnemyBullet();
                    break;
                case HEALTHPACK:
                    entity = createHealthPack();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid entity type: " + entityType);
            }
        }
        return entity;
    }

    public Entity createEntity(int entityTypeKey, int quantity, float x, float y) {
        EntityType entityType = entityTypeMap.get(entityTypeKey);
        Entity entity;
        switch (entityType) {
            case BOY:
                entity = new Boy(
                        entityType.health, entityType.maxHealth, x, y, entityType.scale,
                        new Sprite(new Texture(entityType.texturePath)),
                        entityType.speed, entityType.playerMovement, batch, this, entityManager
                );
                break;
            case ENEMY:
                entity = new Enemy(
                        entityType.health, x, y, entityType.scale,
                        new Sprite(new Texture(entityType.texturePath)),
                        entityType.speed, entityType.defaultChangeRate, batch, this, entityManager
                );
                break;
            case BOSS:
                entity = new Boss(
                        entityType.health, x, y, entityType.scale,
                        new Sprite(new Texture(entityType.texturePath)),
                        entityType.speed, entityType.defaultChangeRate, batch, this, entityManager
                );
                break;
            case BULLET:
                entity = new Bullet(
                        entityType.health, x, y, entityType.scale,
                        new Sprite(new Texture(entityType.texturePath)),
                        entityType.speed, entityType.defaultChangeRate, batch
                );
                break;
            case ENEMYBULLET:
                entity = new EnemyBullet(
                        entityType.health, x, y, entityType.scale,
                        new Sprite(new Texture(entityType.texturePath)),
                        entityType.speed, entityType.defaultChangeRate, batch
                );
                break;
            case HEALTHPACK:
                entity = new HealthPack(
                        entityType.health, x, y, entityType.scale,
                        new Sprite(new Texture(entityType.texturePath)),
                        entityType.speed, entityType.defaultChangeRate, batch
                );
                break;
            default:
                throw new IllegalArgumentException("Invalid entity type: " + entityType);
        }

        // Add the created entity to the EntityManager
        entityManager.addEntity(entity);

        return entity;
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

//    //For shoot function
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
