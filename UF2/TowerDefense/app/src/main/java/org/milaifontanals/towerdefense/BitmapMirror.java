package org.milaifontanals.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class BitmapMirror {

    public static Bitmap mirrorBitmapHorizontally(Bitmap originalBitmap) {
        // Create a matrix for the transformation
        Matrix matrix = new Matrix();

        // Flip horizontally
        matrix.setScale(-1, 1);

        // Set the pivot point to the center of the image
        matrix.postTranslate(originalBitmap.getWidth(), 0);

        // Create a new bitmap to draw onto
        Bitmap mirroredBitmap = Bitmap.createBitmap(
                originalBitmap.getWidth(), originalBitmap.getHeight(), originalBitmap.getConfig());

        // Create a canvas to draw onto the new bitmap
        Canvas canvas = new Canvas(mirroredBitmap);

        // Draw the original bitmap onto the canvas with the matrix transformation
        canvas.drawBitmap(originalBitmap, matrix, new Paint(Paint.FILTER_BITMAP_FLAG));

        return mirroredBitmap;
    }
}