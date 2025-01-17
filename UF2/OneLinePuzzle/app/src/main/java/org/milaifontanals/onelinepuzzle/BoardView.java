package org.milaifontanals.onelinepuzzle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BoardView extends View {

    public static final double PERCENTATGE_BOARD_W = 0.8;
    private Board board;
    private final int marge = 20;//px
    private int wBoard, xBoard, yBoard;
    private int cellSize;
    private Paint paint;

    public BoardView(Context context) {
        this(context, null);
    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        board = new Board(new Point(0, 0), 2);


        getViewTreeObserver().addOnGlobalLayoutListener(() -> {

                    wBoard = (int) (this.getWidth() * PERCENTATGE_BOARD_W);
                    xBoard = (this.getWidth() - wBoard) / 2;

                    cellSize = wBoard / board.getQX() - marge;
                    yBoard = (this.getHeight() -
                            (cellSize + marge) * board.getQY()) / 2;

                    Log.d("XXX", "wBoard: " + wBoard + " xBoard: " + xBoard + " yBoard: " + yBoard + " cellSize: " + cellSize);
        });

        paint = new Paint();
        paint.setColor(Color.argb(255, 150, 100, 100));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int separacio = marge + cellSize;
        Point p = new Point(xBoard, yBoard);

        for (int y = 0; y < board.getQY(); y++) {
            for (int x = 0; x < board.getQX(); x++) {
                if (board.esBuida(x, y))
                {
                    canvas.drawRoundRect(p.x,p.y,p.x+cellSize,p.y+cellSize,50,50,paint);
                }
                p.x += separacio;
            }
            p.x = xBoard;
            p.y += separacio;
        }

    }
}
