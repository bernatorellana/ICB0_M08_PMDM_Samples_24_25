package org.milaifontanals.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;

import java.util.Arrays;
import java.util.LinkedList;

public class Board {


    private Bitmap background;
    private Bitmap mask;

    private Bitmap distancies;
    private float[][] distanciesArray;
    private Point mDesti;

    public Bitmap getBackground() {
        return background;
    }

    public Bitmap getMask() {
        return mask;
    }

    public Bitmap getDistancies() {
        return distancies;
    }

    public Point getmDesti() {
        return mDesti;
    }

    public Board(Context c){
        background = BitmapFactory.decodeResource( c.getResources(),  R.drawable.tower);
        mask = BitmapFactory.decodeResource( c.getResources(),  R.drawable.tower_map);

        distancies = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        distanciesArray = new float[mask.getWidth()][mask.getHeight()];
        // Omplir la matriu amb +INFINITS
        Arrays.stream(distanciesArray).forEach(row -> Arrays.fill(row, Float.MAX_VALUE));


        buscaPuntDesDesti(mask);

        distanciesArray[mDesti.x][mDesti.y]=0;
        expandeixPuntDesti();
    }

    private void expandeixPuntDesti() {

        LinkedList<Point> llistaPunts = new LinkedList<>();
        llistaPunts.add(mDesti);

        while(llistaPunts.size()>0) {
            Point puntActual = llistaPunts.removeFirst();

            float d = distanciesArray[puntActual.x][puntActual.y];

            int[][] delta = {
                    {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
            };
            for (int i = 0; i < delta.length; i++) {
                Point p = new Point(puntActual.x + delta[i][0], puntActual.y + delta[i][1]);
                //Si el píxel està fora...passem d'ell
                if (p.x < 0 || p.x >= mask.getWidth() || p.y < 0 || p.y >= mask.getHeight())
                    continue;
                // Si el píxel és màscara...passem d'ell
                if (Color.red(mask.getPixel(p.x, p.y)) >= 200) continue;

                float novaDistancia = d + (float) Math.sqrt(
                        delta[i][0] * delta[i][0] +
                                delta[i][1] * delta[i][1]);
                if (distanciesArray[p.x][p.y]>novaDistancia ) {
                    distanciesArray[p.x][p.y] = novaDistancia;
                    llistaPunts.add(p);
                }
            }
        }

    }


    private void buscaPuntDesDesti(Bitmap mask) {
        for (int x = 0; x < mask.getWidth(); x++) {
            for (int y = 0; y < mask.getHeight(); y++) {
                int pixel = mask.getPixel(x, y);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                int a = Color.alpha(pixel);
                if(b>250){
                    // Punt de destí
                    mDesti = new Point(x,y);
                }
            }
        }
    }


}
