package main;

import controllers.*;
import utilities.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GamePlay implements GameGame {

    public static boolean[][] wallDown = new boolean[30][30];
    public static boolean[][] wallRight = new boolean[30][30];
    public static boolean playerTurn = true;
    public static int MAP_LEFT = 214, MAP_TOP = 81;

    private LevelManager levelManager;
    private Image background = null;
    public static int exitX = 0, exitY = 0; // chứa tọa độ trên bản đồ game
    public static int screenExitX = 0, screenExitY = 0; // TỌA DỘ EXIT TRÊN MÀN HÌNH
    public static Image exitImage;

    public void init() {
        levelManager = new LevelManager();
    }

    public GamePlay() {
        background = Utils.getImage("backdrop.jpg");
    }

    public void drawExit(Graphics g) {
        int x,y, sql = GameConfig.TILE_LENGTH;
        x = MAP_LEFT + exitX * sql;
        y = MAP_TOP + exitY * sql;
        g.drawImage(exitImage,x,y,sql,sql,null);
    }

    public synchronized void draw(Graphics g) {
        g.drawImage(background,0,0,GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);
        drawExit(g);

        BufferedImage gameMap = new BufferedImage(GameConfig.MAP_SIZE, GameConfig.MAP_SIZE,BufferedImage.TYPE_INT_RGB);
        Graphics screenGraphics = gameMap.getGraphics();
        GameMap.instance.draw(screenGraphics);
        ControllerController.instance.draw(screenGraphics);

        g.drawImage(gameMap,MAP_LEFT,MAP_TOP,360,360,null);
    }

    public synchronized void run() {
        if (levelManager==null) init();
        levelManager.run();
        ControllerController.instance.run();
    }

    public static GamePlay instance = new GamePlay();
}
