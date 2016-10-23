package controllers;

import main.GamePlay;
import models.GameObject;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class EnemyControllerRed extends EnemyController {

    private static int SIZEX = 50, SIZEY = 50;
    private int moveDirections = 4;


    private EnemyControllerRed(int column, int row) {
        super(column,row);
        gameView.setImage("plane2.png");
    }

    public static EnemyControllerRed create(int column, int row) {
        return new EnemyControllerRed(column,row);
    }

    private Point tryDirection(int column0, int row0) {
        int column, row, best = 1000000;
        Point res = new Point(column0,row0);
        GameObject player = PlayerController.instance.getGameObject();

        for (int i=moveDirections;i>=0;i--) {
            column = column0 + dx[i];
            row = row0 + dy[i];
            if (Utils.manDistance(column,row,player.getColumn(),player.getRow()) <= best) {
                best = Utils.manDistance(column,row,player.getColumn(),player.getRow());
                if (Utils.canMoveTo(column0,row0,column,row)) res = new Point(column,row);
            }
        }
        return res;
    }

    public void move(GameObject go) {
        Point target = tryDirection(go.getColumn(),go.getRow());
        go.setColumn(target.x);
        go.setRow(target.y);
    }

    public void draw(Graphics g) {
        gameView.drawImage(g,gameObject);
    }

    public void run() {
        if (GamePlay.playerTurn) return;
        move(gameObject);
        move(gameObject);
    }

}
