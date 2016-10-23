package controllers;

import main.GamePlay;
import models.GameObject;
import models.Mummy;
import utilities.Utils;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class EnemyController extends SingleController {

    private static int SIZEX = 50, SIZEY = 50;
    private int moveDirections = 4;

    private void checkDefault() {
    }

    protected EnemyController(int column, int row) {
        super(new GameObject(column,row,SIZEX,SIZEY), new ImageView("plane3.png"));
        checkDefault();
    }

    public void draw(Graphics g) {
        gameView.drawImage(g,gameObject);
    }

    public void run() {
        if (GamePlay.playerTurn) return;
        move(gameObject);
    }
}
