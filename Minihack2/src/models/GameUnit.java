package models;

import main.GameConfig;
import main.GamePlay;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameUnit extends GameObject {

    public static final int WIDTH = 50, HEIGHT = 50;

    public GameUnit(int column,int row) {
        super(column,row,WIDTH,HEIGHT);
        setColumn(column);
        setRow(row);
    }

    public GameUnit(int column,int row,int width,int height) {
        super(column,row,width,height);
    }
}
