package org.milaifontanals.towerdefense;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {

    private Board board;

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        board = new Board(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(board.getDistancies(), 0, 0, null);
    }


}
