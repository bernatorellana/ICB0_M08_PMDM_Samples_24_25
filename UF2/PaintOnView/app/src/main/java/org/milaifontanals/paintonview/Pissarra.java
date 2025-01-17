package org.milaifontanals.paintonview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Pissarra extends View {
    private Bitmap ghost;
    private int frame = 0;
    public Pissarra(Context context) {
        //super(context);
        this(context, null);
    }

    public Pissarra(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // Crretgar en memòria el bitmap del Màrio
        ghost = BitmapFactory.decodeResource(this.getResources(), R.drawable.red);

    }



    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(100, 100, 50, paint);
        paint.setColor(Color.GREEN);
        canvas.drawRect(100,100,200,200,paint);

        Path p = new Path();
        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeWidth(5);
        float r = 200;
        float xCentre = 300, yCentre=300;
        float xA=-1,yA=-1;
        for(float angle =(float)Math.random()*50.f;angle<=360*5;angle+=10){
            float x = (float)(r*Math.cos(Math.toRadians(angle))) + xCentre;
            float y = (float)(r*Math.sin(Math.toRadians(angle))) + yCentre;

            if(xA!=-1){
                //canvas.drawLine(xA,yA,x,y,paint);
                p.lineTo(x,y);
            } else { // el primer punt
                p.moveTo(x,y);
            }
            r-=  200.0f/ ((360*5)/10.0f);
            xA = x;
            yA = y;
        }
        p.quadTo(0,2500, 1000, 2500);



        canvas.drawPath(p,paint);

        //canvas.drawBitmap(ghost, 100, 100, paint);

        int ghostX, ghostW;
        ghostX = frame * ghost.getWidth()/2;
        ghostW = ghost.getWidth()/2;
        canvas.drawBitmap(ghost, new Rect(ghostX,0, ghostW, ghost.getHeight()),
                new Rect(0,0, ghost.getWidth()/2,ghost.getHeight()), paint);

        frame=(frame+1)%2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("XXX", "XXXXXX");
        return false;
    }
}
