package org.milaifontanals.towerdefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Board board;
    private GameThread thread;

    private List<GameObject> gameObjects = new ArrayList<>();

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        getHolder().addCallback(this);


        board = new Board(context);
        Sprite b1 = new PirateSprite(this, board.getSpawnPoints().get(0));
        Sprite b2 = new PirateSprite(this, board.getSpawnPoints().get(1));
        FireballTurret turret = new FireballTurret(this, new Point(500,1000));
        gameObjects.add(turret);
        gameObjects.add(b1);
        gameObjects.add(b2);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        thread = new GameThread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        thread.stopRunning();
    }

    public void runTurn() {
        for(int i=0;i<gameObjects.size();i++) {
            GameObject go = gameObjects.get(i);
            go.update();
        }


    }

    public void paintScene(Canvas c) {

        c.drawBitmap(board.getBackground(), 0, 0, null);

        for (GameObject go : gameObjects) {
            go.draw(c);
        }

    }

    public Board getBoard() {
        return board;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
