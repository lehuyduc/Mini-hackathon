package models;

/**
 * Created by Le Huy Duc on 27/10/2016.
 */
public class Explosion extends GameObject {

    public static final int SIZE = 60;

    public Explosion(int column,int row) {
        super(column,row,SIZE,SIZE);
    }
}
