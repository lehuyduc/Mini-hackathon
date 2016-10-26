package main;

import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 25/10/2016.
 */
public class GameVictory implements GameGame {

    public void init() {
        Background.instance.init();
        Background.background = Utils.getImage("findtreasure.jpg");
    }

    private GameVictory() {
        init();
    }

    public void draw(Graphics g) {
        Background.instance.draw(g);
    }

    public void run() {

    }

    public static GameVictory instance = new GameVictory();

}
