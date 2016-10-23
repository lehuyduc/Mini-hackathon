package models;

import main.GameConfig;
import main.GamePlay;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameObject {
    public static final int TILE_LENGTH = GamePlay.TILE_LENGTH;
    protected int x = 0, y = 0;
    protected int row = 0, column = 0;
    protected int width = 0, height = 0, cornerX = 0, cornerY = 0;

    public int getX() {return x;}

    public int getY() {return y;}

    public int getCornerX() {return cornerX;}

    public int getCornerY() {return cornerY;}

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int v) {x = v; cornerX = x - width/2;}

    public void setY(int v) {y = v; cornerY = y - height/2;}

    public void setWidth(int width) {
        this.width = width;
        this.cornerX = x - width/2;
    }

    public void setHeight(int height) {
        this.height = height;
        this.cornerY = y - height/2;
    }

    public void setColumn(int v) {
        int sql = GamePlay.TILE_LENGTH;
        column = v;
        setX(column * sql + sql/2);
    }

    public void setRow(int v) {
        int sql = GamePlay.TILE_LENGTH;
        row = v;
        setY(row * sql + sql/2);
    }

    public void move(int dx,int dy) {
        column += dx;
        row += dy;
    }

    public GameObject() {

    }


    public GameObject(int column, int row,int width,int height) {
        setColumn(column);
        setRow(row);
        setWidth(width);
        setHeight(height);
    }
}
