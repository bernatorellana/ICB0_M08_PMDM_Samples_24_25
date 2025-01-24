package org.milaifontanals.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class Board {


    private Bitmap background;
    private Bitmap mask;

    public Board(Context c){
        background = BitmapFactory.decodeResource( c.getResources(),  R.drawable.tower);
        mask = BitmapFactory.decodeResource( c.getResources(),  R.drawable.tower_map);

        recorrerTotsElsPixelsDeLaImatge(mask);

    }

    private void recorrerTotsElsPixelsDeLaImatge(Bitmap mask) {
        for (int x = 0; x < mask.getWidth(); x++) {
            for (int y = 0; y < mask.getHeight(); y++) {
                int pixel = mask.getPixel(x, y);
                int r = Color.red(pixel);
            }
        }
    }


}
