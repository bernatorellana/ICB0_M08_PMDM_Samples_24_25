package org.milaifontanals.towerdefense;

import android.graphics.Canvas;
import android.graphics.Point;

public abstract class GameObject {

    protected GameView gv;

    protected Point position;

    public GameObject(GameView gv, Point position) {
        this.gv = gv;
        this.position = position;
    }

    public abstract void draw(Canvas c);

    public abstract void update();


    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }

    public GameView getGameView() {
        return gv;
    }
    public void setGameView(GameView gv) {
        this.gv = gv;
    }


}
