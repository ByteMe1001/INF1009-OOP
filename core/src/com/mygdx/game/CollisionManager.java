package com.mygdx.game;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.util.iCollision;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private List<Entity> collisionList;

    public CollisionManager(List<Entity> entityList) {

        collisionList = new ArrayList<Entity>();

        for (Entity entity : entityList) {
            if (entity.isCollidable()) {
                collisionList.add(entity);
                System.out.println(entity + "added to collide list");
            }
            //System.out.println(collidableList);
        }
    }

    public void detectCollisions(EntityManager entityManager) {
        for (int i = 0; i < collisionList.size() - 1; i++) {
            //System.out.println(collidableList.get(i));
            for (int j = i + 1; j < collisionList.size(); j++) {
                //System.out.println(collidableList.get(j));


                if (collisionList.get(i).collidesWith(collisionList.get(j))) {
//                  collidableList.get(i).onCollision(collidableList.get(j));
//                  collidableList.get(j).onCollision(collidableList.get(i));
                    //System.out.println(collidableList.get(i));
                    System.out.println(collisionList.get(i) + " collides with " + collisionList.get(j));
                    System.out.println(collisionList.get(i) + " " + collisionList.get(i).getBoundingBox());
                    System.out.println(collisionList.get(j) + " " + collisionList.get(j).getBoundingBox());
                    System.out.println("Collision Detected");
                    collisionHandler(entityManager, collisionList.get(i), collisionList.get(j));

                }


            }
        }
    }

    public void collisionHandler(EntityManager entityManager, Entity x, Entity y){
        //Player.score()

        //Insert Collision Logic here

        collisionList.remove(x);
        //entityManager.removeEntity(x);
        collisionList.remove(y);
        //entityManager.removeEntity(y);
        System.out.println(x + " and " + y + "has been removed from the Collision List");


    }
}

/*
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
                    Droplet.collideWith(Bucket);
                    Bucket.collideWith(Droplet);
                }
            }
        }
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
