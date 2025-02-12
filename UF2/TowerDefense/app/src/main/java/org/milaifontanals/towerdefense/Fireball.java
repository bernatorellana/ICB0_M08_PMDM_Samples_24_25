package org.milaifontanals.towerdefense;

import android.graphics.Point;

public class Fireball extends Sprite{

    GameObject closestEnemy;
    public Fireball(GameView gameView, Point position, GameObject closestEnemy) {
        super(gameView, position);
        if(! (closestEnemy instanceof Enemy)) throw new RuntimeException("Enemy expected");
        this.closestEnemy = closestEnemy;
    }

    @Override
    public int getNumFrames() {
        return 6;
    }

    @Override
    public int getSpriteResource() {
        return R.drawable.fireball;
    }

    @Override
    public boolean centerOnFoot() {
        return false;
    }

    public float getScale(){
        return 0.4f;
    }

    @Override
    public void update() {
        super.update();
        if(closestEnemy!=null){
            int dx = closestEnemy.getPosition().x - this.getPosition().x;
            int dy = closestEnemy.getPosition().y - this.getPosition().y;

            int distance = (int) Math.sqrt( dx*dx + dy*dy);

            float speed = 10;

            float desplX = 0;
            float desplY = 0;
            if(distance>speed){
                desplX = speed * dx/distance;
                desplY = speed * dy/distance;

                this.setPosition(
                        new Point((int)(this.getPosition().x + desplX),
                                (int)(this.getPosition().y + desplY))
                );
            } else {
                // Matar enemic
                ((Enemy)closestEnemy).hit(1);
                gv.getGameObjects().remove(this);
            }

        }
    }
}
