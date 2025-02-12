package org.milaifontanals.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Ball extends GameObject {

    private Paint paint;

    public Ball(GameView gv, Point position) {
        super(gv, position);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public void draw(Canvas c) {
        c.drawCircle(getPosition().x, getPosition().y, 50, paint);
    }

    @Override
    public void update() {

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
        this.setPosition(candidat);




        }
}
