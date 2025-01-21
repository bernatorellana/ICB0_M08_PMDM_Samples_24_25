package org.milaifontanals.onelinepuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BoardView extends View {

    public static final double PERCENTATGE_BOARD_W = 0.8;


    private enum Estat {
        IDLE,
        DRAWING
    }

    private Estat estat = Estat.IDLE;

    private Board board;
    private final int marge = 20;//px
    private int wBoard, xBoard, yBoard;

    private  int cellSizeAmbMarge;
    private int cellSize;
    private Paint paint;

    private List<Point> currentPath;


    private Bitmap bitmapObstacle;

    public BoardView(Context context) {
        this(context, null);
    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        board = new Board(new Point(0, 0), 2);



        bitmapObstacle = BitmapFactory.decodeResource(this.getResources(),
                            R.drawable.obstacle);

        getViewTreeObserver().addOnGlobalLayoutListener(() -> {

                    wBoard = (int) (this.getWidth() * PERCENTATGE_BOARD_W);
                    xBoard = (this.getWidth() - wBoard) / 2;

                    cellSizeAmbMarge = wBoard / board.getQX();
                    cellSize = cellSizeAmbMarge - marge;

                    yBoard = (this.getHeight() -
                            (cellSize + marge) * board.getQY()) / 2;

                    currentPath = new ArrayList<>();
                    Point p = board.getInici();
                    currentPath.add(convertCell2PixelCoords(p) );
                    //p.x+=1;
                    //currentPath.add(convertCell2PixelCoords(p) );
                    //p.y+=1;
                    //currentPath.add(convertCell2PixelCoords(p) );



                    Log.d("XXX", "wBoard: " + wBoard + " xBoard: " + xBoard + " yBoard: " + yBoard + " cellSize: " + cellSize);
        });

        paint = new Paint();

    }

    @NonNull
    private Point convertCell2PixelCoords(Point p) {
        return new Point(
                (int) (xBoard + cellSizeAmbMarge * p.x + cellSize * 0.5f),
                (int) (yBoard + cellSizeAmbMarge * p.y + cellSize * 0.5f));
    }
    private Point convertPixel2CellCoords(Point p) {
        return new Point(
                (int) ((p.x - xBoard) / cellSizeAmbMarge),
                (int) ((p.y - yBoard) / cellSizeAmbMarge));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        Point c = convertPixel2CellCoords(new Point((int) event.getX(), (int) event.getY()));

        if(estat==Estat.IDLE) {
            if(c.equals(board.getInici())){
                estat = Estat.DRAWING;
            }
        } else if (estat==Estat.DRAWING) {
            if(event.getActionMasked()==MotionEvent.ACTION_UP){
                estat = Estat.IDLE;
            } else {
                if(board.esObstacle(c.x, c.y)){
                    estat = Estat.IDLE;
                } else {
                    Point p = convertCell2PixelCoords(c);

                 if(!currentPath.contains(p)) {
                     currentPath.add(p);
                 }
                }
            }
        }


        //currentPath.add(convertCell2PixelCoords(c));
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.argb(255, 150, 100, 100));
        paint.setStyle(Paint.Style.FILL);


        int separacio = marge + cellSize;
        Point p = new Point(xBoard, yBoard);

        canvas.drawColor(Color.parseColor("#ffaaee"));

        // Dibuixem el tauler
        for (int y = 0; y < board.getQY(); y++) {
            for (int x = 0; x < board.getQX(); x++) {
                if (board.esBuida(x, y))
                {
                    canvas.drawRoundRect(p.x,p.y,p.x+cellSize,p.y+cellSize,50,50,paint);
                } else if(board.esObstacle(x,y)){
                    canvas.drawBitmap(bitmapObstacle,
                            new Rect(0, 0, bitmapObstacle.getWidth(), bitmapObstacle.getHeight()),
                            new Rect(p.x, p.y, p.x + cellSize, p.y + cellSize),
                            paint);
                }
                p.x += separacio;
            }
            p.x = xBoard;
            p.y += separacio;
        }
        // Dibuixem el camí per on passa el dit
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(30);
        paint.setPathEffect(new DashPathEffect(new float[]{50, 10}, 0));        int radi = 40;
        Point lp=null;
        Path path = new Path();
        for(Point cp: currentPath){
            canvas.drawCircle(cp.x , cp.y, radi, paint);
            if(lp!=null){
                // a partir del segon punt
                path.lineTo(cp.x, cp.y);
                canvas.drawLine(lp.x, lp.y, cp.x, cp.y, paint);
            } else {
                // pel primer punt del camí
                path.moveTo(cp.x, cp.y);
            }
            lp = cp;
        }
        Paint brochaGorda = new Paint();
        brochaGorda.setColor(Color.parseColor("#66ff0088"));
        brochaGorda.setStrokeWidth(cellSize*0.8f);
        brochaGorda.setStyle(Paint.Style.STROKE);
        brochaGorda.setStrokeCap(Paint.Cap.ROUND);
        brochaGorda.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, brochaGorda);

    }
}
