package controllers;

import main.GamePlay;

import java.awt.*;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class EnemyControllerScorpion extends EnemyController {

    private EnemyControllerScorpion(int column,int row) {
        super(column,row);
        gameView.setImage("scorpion6.gif");
    }

    public static EnemyControllerScorpion create(int column,int row) {
        return new EnemyControllerScorpion(column,row);
    }

    void move() {

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
