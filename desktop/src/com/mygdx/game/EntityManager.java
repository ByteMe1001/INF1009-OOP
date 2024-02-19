import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {
    private List<Entity> entities;
    private ConflictManager conflictManager;
    private SpriteBatch spriteBatch;
    
    //Constructor with collisionManager as an instance variable
    public EntityManager(ConflictManager conflictManager) {
        entities = new ArrayList<>();
        this.conflictManager = conflictManager;
    }
    //Add
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    //Remove
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    //Iterates through entites and calls their update method
    public void updateEntities() {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update();

            if (entity.isDestroyed()) {
                iterator.remove();
            }
        }
        // Handle collisions using the ConflictManager
        conflictManager.handleCollisions(entities);
    }
    //Rendering method
    public void renderEntities(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        for (Entity entity : entities) {
            entity.render(spriteBatch, shapeRenderer);
        }
    }
}
