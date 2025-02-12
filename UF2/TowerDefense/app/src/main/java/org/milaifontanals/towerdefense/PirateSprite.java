package org.milaifontanals.towerdefense;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

public class PirateSprite extends Sprite implements Enemy{
    private Integer life =2;
    public PirateSprite(GameView gv, Point position) {

        super(gv, position);

    }

    @Override
    public int getNumFrames() {
        Log.d("XXX", "getNumFrames:"+life);
         if(life==null || life>0) return 10; else return 4;
    }

    public float getScale(){
        return 0.5f;
    }


    @Override
    public int getSpriteResource() {
        if(life==null || life>0) return R.drawable.sprite_skeleton_pirate;
        else return R.drawable.rotating_skull;
    }

    @Override
    public boolean centerOnFoot() {
        return true;
    }

    public void update() {
        super.update();

        if(life<=0 && deadTime<System.currentTimeMillis()) {
             gv.getGameObjects().remove(this);
        }


        float[][] distanciesArray = gv.getBoard().getDistanciesArray();
        //this.position.x


        int[][] delta = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };
        float min = distanciesArray[getPosition().x][getPosition().y];
        Point candidat=getPosition();
        for (int i = 0; i < delta.length; i++) {
            Point p = new Point(getPosition().x + 5*delta[i][0],
                    getPosition().y + 5*delta[i][1]);
            if( distanciesArray[p.x][p.y]<min) {
                min = distanciesArray[p.x][p.y];
                candidat = p;
            }
        }
        setPosition(candidat);
    }

    private long deadTime = -1;
    @Override
    public void hit(int damage) {
        life-=damage;
        Log.d("XXX", "HIT:"+life);
        if(life<=0){
           // gv.getGameObjects().remove(this);
            initializeSprite(gv);
            deadTime = System.currentTimeMillis() + 1000;
        }
    }
}
