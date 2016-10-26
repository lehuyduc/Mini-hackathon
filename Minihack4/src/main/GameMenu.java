package main;

import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 22/10/2016.
 */
public class GameMenu implements GameGame {

    private Image background = null;

    public void init() {
        background = Utils.getImage("menuback.jpg");
    }

    private GameMenu() {
        init();
    }

    public void draw(Graphics g) {
        g.drawImage(background,0,0,GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);
    }

    public void run() {

    }

    public static GameMenu instance = new GameMenu();
}
