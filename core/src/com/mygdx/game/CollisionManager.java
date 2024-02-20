package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class CollisionManager implements iCollision {

    float x, y;
    int width, height;
    private List<Droplet> Droplet;
    private List<Bucket> Bucket;

    private List<Entity> entities;

    // Default constructor
    public CollisionManager() {
    }

    public CollisionManager(float x, float y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void detectCollision(List<Entity> entities){
        for(Entity Droplet: entities){
            for (Entity Bucket: entities){
                if(Droplet != Bucket && Bucket.getBoundingBox().overlaps(Droplet.getBoundingBox())){
                    System.out.println("");
                }
            }
        }
    }

    @Override
    public boolean collidesWith(CollisionManager rect) {
        return false;
    }
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


    private boolean detectCollisions() {
        ListIterator<Droplet> dropletIterator = Droplet.listIterator();
        while (dropletIterator.hasNext()) {
            Droplet droplet = dropletIterator.next();
            ListIterator<Bucket> bucketIterator = Bucket.listIterator();
            while (bucketIterator.hasNext()) {
                Bucket bucket = bucketIterator.next();

                if (bucket.intersects(droplet.boundingBox)) { //based on youtube tutorial, getBoundingBox returns a rectangle
                    if (droplet.collideWith(bucket)) {

                        System.out.println("Collision Detected");
                        bucketIterator.remove();
                        //Entity.setAlive(false) ;
                        return true;
                    }
                }
            }
        }
        return false;
    }



    private boolean detectCollision() {

        for (Droplet droplet : Droplet) {
            for (Bucket bucket : Bucket) {
                /*if (droplet.getRect().collidesWith(bucket.getRect())){
                    System.out.println("Collision Detected");
                    return true;
                }

            }
                if (bucket.boundingBox.overlaps(droplet.boundingBox)) {

                        System.out.println("Collision Detected");
                        return true;

                }
            }

        }
        return false;
    }

    public void move(float x, float y){ //x and y of what we are colliding is not the same so will need to move the rect as well
        this.x  = x;
        this.y = y;
    }

    public boolean collidesWith(CollisionManager rect){
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
     }
}


*/

//No Collision, Got Collision
//collisionManager get entityManager

//Destroy or stay put for results

//if collide is true and collidable is true

//need to call entity manager to update
