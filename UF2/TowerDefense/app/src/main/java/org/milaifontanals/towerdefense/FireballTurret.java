package org.milaifontanals.towerdefense;

import android.graphics.Point;

import java.util.List;

public class FireballTurret extends Sprite{
    public FireballTurret(GameView gv, Point position) {
        super(gv, position);
    }

    @Override
    public int getNumFrames() {
        return 1;
    }

    @Override
    public int getSpriteResource() {
        return R.drawable.inferno_tower;
    }

    @Override
    public boolean centerOnFoot() {
        return false;
    }

    public float getScale(){
        return 0.4f;
    }

    long lastShot = 0;

    @Override
    public void update() {
        super.update();
        long now = System.currentTimeMillis();
        if(now-lastShot>2000) {
            //dispara !!!!
            lastShot = now;
            //Fireball fb = new Fireball(getGameView(), getPosition());
            //getGameView().gameObjects.add(fb);

            int minDistance = Integer.MAX_VALUE;
            GameObject closestEnemy = null;

            List<GameObject> gameObjects =
                    this.getGameView().getGameObjects();
            for (GameObject o : gameObjects) {
                if(o instanceof Enemy) {

                    int dx = o.getPosition().x - this.getPosition().x;
                    int dy = o.getPosition().y - this.getPosition().y;

                    int distance = (int) Math.sqrt( dx*dx + dy*dy);
                    if(distance<minDistance) {
                        minDistance = distance;
                        closestEnemy = o;
                    }
                }
            }
            if(closestEnemy!=null) {
                Fireball fb = new Fireball(getGameView(), getPosition(), closestEnemy);
                getGameView().getGameObjects().add(fb);
            }

        }
    }
}
