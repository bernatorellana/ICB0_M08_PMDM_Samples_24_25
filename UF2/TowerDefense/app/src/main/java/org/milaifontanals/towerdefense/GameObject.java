package org.milaifontanals.towerdefense;

import android.graphics.Canvas;
import android.graphics.Point;

public abstract class GameObject {

    protected GameView gv;

    private Point position, oldPosition;
    private Point direction;

    public GameObject(GameView gv, Point position) {
        this.gv = gv;
        this.position = position;
    }



    public abstract void draw(Canvas c);

    public abstract void update();


    public Point getDirection() {
        return direction;
    }

    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.oldPosition = this.position;
        this.position = position;
        this.direction = new Point(
                position.x - oldPosition.x,
                position.y - oldPosition.y);
    }

    public GameView getGameView() {
        return gv;
    }
    public void setGameView(GameView gv) {
        this.gv = gv;
    }


}
