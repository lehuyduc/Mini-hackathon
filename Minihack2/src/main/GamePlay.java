package main;

import controllers.*;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GamePlay implements GameGame {

    public static int MAP_WIDTH = 6,
                      MAP_HEIGHT = 6;
    public static int MAP_TOP = 1, MAP_LEFT = 1;
    public static int TILE_LENGTH = GameConfig.DEFAULT_TILE_LENGTH;
    public static int WALL_DOWN_HEIGHT = GameConfig.DEFAULT_DOWN_WALL_HEIGHT;
    public static int WALL_RIGHT_WIDTH = GameConfig.DEFAULT_RIGHT_WALL_WIDTH;

    public static boolean[][] wallDown = new boolean[30][30];
    public static boolean[][] wallRight = new boolean[30][30];
    public static boolean playerTurn = true;

    private LevelManager levelManager;
    public static int exitX = 12312365, exitY = -123954;

    public void init() {
        levelManager = new LevelManager();
        Background.instance.init();
        Background.background = Utils.getImage("floor6.jpg");
    }

    public GamePlay() {

    }

    public synchronized void draw(Graphics g) {
        Background.instance.draw(g);
        ControllerController.instance.draw(g);
    }

    public synchronized void run() {
        if (levelManager==null) init();
        levelManager.run();
        ControllerController.instance.run();
    }

    public static GamePlay instance = new GamePlay();
}
