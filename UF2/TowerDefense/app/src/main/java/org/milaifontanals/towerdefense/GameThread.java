package org.milaifontanals.towerdefense;

import android.graphics.Canvas;

import java.util.Date;

public class GameThread extends Thread{

    private GameView gv;
    private long milisPerFrame = 1000/30;
    public GameThread(GameView gv){
        this.gv = gv;
    }

    boolean isRunning = true;

    @Override
    public void run() {
        super.run();

        while(isRunning){
            long time_before = (new Date()).getTime();
            Canvas c = gv.getHolder().lockCanvas();
            gv.runTurn();
            gv.paintScene(c);
            long time_after = (new Date()).getTime();
            long waitTime = milisPerFrame - (time_after - time_before);
            if(waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            gv.getHolder().unlockCanvasAndPost(c);

        }
    }

    public void stopRunning(){
        this.isRunning = false;
    }

}
