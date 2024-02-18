package com.mygdx.game;

abstract public class CollisionManager implements iCollision{

    float x, y;
    int width, height;



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
     @Override
    public boolean collidesWith(CollisionManager rect){
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
     }
}
