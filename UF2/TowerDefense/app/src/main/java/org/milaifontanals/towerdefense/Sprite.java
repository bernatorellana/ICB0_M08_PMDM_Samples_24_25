package org.milaifontanals.towerdefense;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import androidx.annotation.DrawableRes;

public abstract class Sprite extends GameObject{

    Bitmap bitmap, flippedBitmap;
    int frames;
    int frameW,frameH;



    int currentFrame = 0;
    public Sprite(GameView gv, Point position) {
        super(gv, position);

        initializeSprite(gv);
    }

    protected void initializeSprite(GameView gv) {
        currentFrame = 0;
        this.frames = getNumFrames();
        bitmap = BitmapFactory.decodeResource(gv.getResources(), getSpriteResource());
        frameW = bitmap.getWidth() / frames;
        frameH = bitmap.getHeight();

        flippedBitmap = BitmapMirror.mirrorBitmapHorizontally(bitmap);
    }

    public abstract int getNumFrames();
    public abstract @DrawableRes int getSpriteResource();

    public abstract boolean centerOnFoot();

    public abstract float getScale();

    @Override
    public void draw(Canvas c) {
        float scale = getScale();
        int cf = currentFrame;
        Bitmap b = flippedBitmap;
        if(getDirection()!=null && getDirection().x<0) {
            cf = frames - 1 - currentFrame;
            b = bitmap;
        }

        int baseX = getPosition().x - (int)(scale*frameW/2);
        int baseY = getPosition().y - (int)(scale*frameH/(centerOnFoot()?1.2:2));
        c.drawBitmap(b, new Rect(cf * frameW, 0,
                                      (cf+1) * frameW, frameH),
                new Rect(baseX, baseY,
                        baseX + (int)(scale*frameW),
                        baseY + (int)(scale*frameH)), null);
    }

    @Override
    public void update() {

        if(getDirection()!=null &&
                (getDirection().x!=0 || getDirection().y!=0)
        ) {
            currentFrame = (currentFrame + 1) % frames;
        }
    }
}
