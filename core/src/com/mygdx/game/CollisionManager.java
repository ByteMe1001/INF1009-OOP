package com.mygdx.game;

public class CollisionManager implements iCollision{

    float x, y;
    int width, height;

    // Default constructor
    public CollisionManager() {
    }

    public CollisionManager (float x, float y, int width, int height){
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    //private Entity entity;


    /*@Override
        public void enemyCollide() {
            if(entity.x == entity.x) {
                entity.destroy();
            }
        }

        @Override
        public void playerCollide() {

        }
        */
/*
     private void detectCollisions(){
         ListIterator<Entity> iterator = entity.ListIterator();
         while(iterator.hasNext()){
             Entity entity0 = iterator.next();
             if(entity2.intersects(entity.getBoundingBox())){ //based on youtube tutorial, getBoundingBox returns a rectangle
                 entity2.hit(entity0);
                 iterator.remove();
             }
         }
     }
 */

    public void move(float x, float y){ //x and y of what we are colliding is not the same so will need to move the rect as well
        this.x  = x;
        this.y = y;
    }

    public boolean collidesWith(CollisionManager rect){
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
     }
}



//No Collision, Got Collision
//collisionManager get entityManager

//Destroy or stay put for results

//if collide is true and collidable is true

//need to call entity manager to update
