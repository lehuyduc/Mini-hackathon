package models;

import main.GamePlay;
import models.GameObject;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public class Trap extends GameObject {

    public static int SIZE = GamePlay.TILE_LENGTH * 3/4;

    public Trap(int column,int row) {
        super(column,row,SIZE,SIZE);
    }
}
