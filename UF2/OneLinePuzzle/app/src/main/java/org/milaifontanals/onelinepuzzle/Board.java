package org.milaifontanals.onelinepuzzle;

import android.graphics.Point;

public class Board {

    int [][] board;
    int QX = 4;
    int QY = 6;

    Point inici;

    int obstacles;

    public Board(Point p, int obstacles){
        this.obstacles = obstacles;
        board = new int[QX][QY];
        inici = p;
        board[p.x][p.y] = 2;
        int obstaclesPosats = 0;
        while(obstaclesPosats < obstacles){
            int x = (int)(Math.random() * QX);
            int y = (int)(Math.random() * QY);
            if(board[x][y] == 0 ){
                board[x][y] = 1;
                obstaclesPosats++;
            }
        }
    }

    public boolean esBuida(int x, int y) {
        return (board[x][y] == 0);
    }

    public Point getInici(){
        return inici;
    }

    public int getQX() {
        return QX;
    }

    public int getQY() {
        return QY;
    }

    public boolean esObstacle(int x, int y) {
        return (board[x][y] == 1);
    }

    public int getFreeCells() {
        return getQX() * getQY() - this.obstacles;
    }
}
