package main;

import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 22/10/2016.
 */
public class GameMenu implements GameGame {

    public void init() {
        Background.instance.init();
        Background.background = Utils.getImage("menuback.jpg");
    }

    private GameMenu() {
        init();
    }

    public void draw(Graphics g) {
        Background.instance.draw(g);
    }

    public void run() {

    }

    public static GameMenu instance = new GameMenu();
}
