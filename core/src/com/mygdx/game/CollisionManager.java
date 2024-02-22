package com.mygdx.game;

import com.mygdx.game.entity.Entity;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.util.iCollision;


public class CollisionManager implements iCollision {
    private List<Entity> collisionList;


    public CollisionManager(List<Entity> entityList, List<Entity> aiEntityList) {
        collisionList = new ArrayList<Entity>();

        for (Entity entity : entityList) {
            if (entity.isCollidable()) collisionList.add(entity); //add collidable objects into a entityList
        }

        for (Entity entity : aiEntityList) {
            if (entity.isCollidable()) collisionList.add(entity); //add collidable objects into a entityList
        }
    }

    // Default constructor
    public CollisionManager() {

    }

    public void detectCollision(EntityManager entityManager){
        //System.out.println(collidableList);
        for (int i = 0; i < collisionList.size() - 1; i++){
            for (int j = i + 1; j < collisionList.size(); j++){
                if(collidesWith(collisionList.get(i), collisionList.get(j))){ //If entities overlaps with one another, print Collision Detected in console
                    System.out.println("Collision Detected");
                    //System.out.println(collidableList.get(i).getBoundingBox());
                    //System.out.println(collidableList.get(j).getBoundingBox());
                    //System.out.println("Bounding Box Values: ", collidableList.get(i).getBoundingBox());
                    handleCollision(entityManager, collisionList, collisionList.get(i), collisionList.get(j));



                }
            }
        }
    }

    public void updateCollisionList(List<Entity> entityList , List<Entity> aiEntityList){  //
        collisionList.clear();
        for (Entity entity : entityList) {
            if (entity.isCollidable()) collisionList.add(entity); //add collidable objects into a entityList

        }
        for (Entity entity : aiEntityList) {
            if (entity.isCollidable()) collisionList.add(entity); //add collidable objects into a entityList
            
        }
        /*
        System.out.println("Collision Entities: " + collidableList );
        System.out.println("Entity List:" + entityList);
        System.out.println("AI Entity List: " + aiEntityList);

         */
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

    public void detectCollision(List<Entity> entityList){
        for(Entity Droplet: entityList){
            for (Entity Bucket: entityList){
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
