/* package com.mygdx.game;

import java.util.Random;

public class AiMovement implements iAiMovement {
    private Random random = new Random();
    private float maxMoveDistance;

    public AiMovement(float maxMoveDistance) {
        this.maxMoveDistance = maxMoveDistance;
    }

    @Override
    public void move(Entity entity) {
        // Generate a random change in position
        float deltaX = (random.nextFloat() * 2 - 1) * maxMoveDistance; // Random value between -maxMoveDistance and +maxMoveDistance
        float deltaY = (random.nextFloat() * 2 - 1) * maxMoveDistance; // Random value between -maxMoveDistance and +maxMoveDistance

        // Update the entity's position
        entity.setPosX(entity.getPosX() + deltaX);
        entity.setPosY(entity.getPosY() + deltaY);
    }
} 


// THIS CODE IS FOR NEXT TIME WHEN WE ACTUALLY IMPLEMENT RANDOM MOVEMENTS

*/

package com.mygdx.game;

public class AiMovement implements iAiMovement {

    public AiMovement() {
        // Constructor empty since no initialization is required
    }

    @Override
    public void move(Entity entity) {
        System.out.println("This is where AI movement will be implemented for entity ID: " + entity.getId());
    }
}

