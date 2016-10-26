package main;

import controllers.*;
import utilities.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GamePlay implements GameGame {

    public static int MAP_TILE_WIDTH = 6,
                      MAP_TILE_HEIGHT = 6;
    public static int MAP_TILE_TOP = 1, MAP_TILE_LEFT = 1;
    public static int MAP_WIDTH = GameConfig.DEFAULT_MAP_WIDTH,
                      MAP_HEIGHT = GameConfig.DEFAULT_MAP_HEIGHT;
    public static int TILE_LENGTH = GameConfig.DEFAULT_TILE_LENGTH;
    public static int WALL_DOWN_HEIGHT = GameConfig.DEFAULT_DOWN_WALL_HEIGHT;
    public static int WALL_RIGHT_WIDTH = GameConfig.DEFAULT_RIGHT_WALL_WIDTH;

    public static boolean[][] wallDown = new boolean[30][30];
    public static boolean[][] wallRight = new boolean[30][30];
    public static boolean playerTurn = true;

    private LevelManager levelManager;
    private Image background = null;
    public static int exitX = 0, exitY = 0;

    public void init() {
        levelManager = new LevelManager();
    }

    public GamePlay() {
        background = Utils.getImage("backdrop.jpg");
    }

    public synchronized void draw(Graphics g) {
        g.drawImage(background,0,0,GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);

        BufferedImage screen = new BufferedImage(GamePlay.MAP_WIDTH,GamePlay.MAP_HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics screenGraphics = screen.getGraphics();
        GameMap.instance.draw(screenGraphics);
        ControllerController.instance.draw(screenGraphics);
        g.drawImage(screen,210,80,360,360,null);
    }

    public synchronized void run() {
        if (levelManager==null) init();
        levelManager.run();
        ControllerController.instance.run();
    }

    public static GamePlay instance = new GamePlay();
}
