package controllers;

import main.GamePlay;
import models.GameObject;
import models.Mummy;
import utilities.Utils;
import views.AnimationView;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class EnemyController extends SingleControllerWithAnimation {

    private static int SIZEX = 50, SIZEY = 50;
    protected int moveDirections = 4;
    protected int maxMoveStep = 0;

    protected EnemyController(int column, int row) {
        super();
        gameObject = new GameObject(column,row,SIZEX,SIZEY);
        gameView = new AnimationView();
        animationView = (AnimationView)gameView;
    }

    public void draw(Graphics g) {
        gameView.drawImage(g,gameObject);
    }

    public void run() {
        if (GamePlay.playerTurn) return;
        move(gameObject);
    }
}
