package controllers;

import main.GameConfig;
import main.gameScreens.PlayGameScreen;
import models.GameObject;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class EnemyControllerWhite extends EnemyController implements Colliable {

    private static int SIZEX = 50, SIZEY = 50;
    private int moveStep;

    private EnemyControllerWhite(int column, int row) {
        super(column,row);
        unitName = "mummy";
        moveDirections = 4;
        maxMoveStep = 2;
        gameObject.setPowerLevel(2);
        gameObject.setHealth(2);
    }

    public static EnemyControllerWhite create(int column, int row) {
        return new EnemyControllerWhite(column,row);
    }

    public void init() {
        moveStep = maxMoveStep;
    }

    //**********  COLLISION *********************************************************************
    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof EnemyController) {
            GameObject go = col.getCollisionObject();
            go.takeDamage(gameObject.getPowerLevel());
        }
        if (col instanceof PlayerController) {
            GameObject go = col.getCollisionObject();
            go.takeDamage(1000);
        }
    }

    // Mummy trắng đi ngang trước dọc sau
    private Point tryDirection(int column0, int row0) {
        int column, row, sql = GameConfig.DEFAULT_TILE_LENGTH, best = 1000000;
        Point res = new Point(column0,row0);
        GameObject player = PlayerController.instance.getGameObject();

        for (int i=0;i<=moveDirections;i++) {
            column = column0 + dx[i];
            row = row0 + dy[i];
            if (Utils.manDistance(column,row,player.getColumn(),player.getRow()) <= best) {
                best = Utils.manDistance(column,row,player.getColumn(),player.getRow());
                if (Utils.canMoveTo(column0,row0,column,row)) {
                    res = new Point(column,row);
                    moveType = moveTypes[i];
                    beginPoint = new Point(gameObject.getX(),gameObject.getY());
                    targetPoint = new Point(column*sql+sql/2,row*sql+sql/2);
                    targetGrid = new Point(column,row);
                }
            }
        }
        return res;
    }

    public void move(GameObject go) {
        Point target = tryDirection(go.getColumn(),go.getRow());
        if (target.x==getColumn() && target.y==getRow()) return; // do nothing because already there
        isMoving = true;
        animationView.firstImage = System.currentTimeMillis();
    }

    public boolean finished() {
        return (!isMoving) && (!isFighting) && moveStep==0;
    }

    public void draw(Graphics g) {
        if (animationView.nImage==0) animationView.setSheet("mummy_down.png",5);
        if (isMoving) animationView.drawImage(g,gameObject);
        else animationView.drawImage(g,gameObject,true);
    }

    public void run() {
        if (isMoving) {moveAnimation(); return;}
        if (PlayGameScreen.playerTurn) return;
        if (moveStep > 0) {moveStep--; move(gameObject);}
    }
}
