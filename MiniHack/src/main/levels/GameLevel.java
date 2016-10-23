package main.levels;

import controllers.*;
import main.Background;
import main.GamePlay;
import models.GameObject;
import utilities.Utils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class GameLevel {

    protected int MAP_WIDTH = 6,
            MAP_HEIGHT = 6;
    protected int MAP_TOP = 1, MAP_LEFT = 1;
    protected Point[] wallDowns;
    protected boolean[][] wallDown;
    protected Point[] wallRights;
    protected boolean[][] wallRight;
    protected Image[][] backgroundTile;
    protected EnemyController[] mummyControllers = new EnemyController[20];

    private int exitX = -124443, exitY = 123123;
    private Scanner input;
    
    public GameLevel() {
        wallDowns = new Point[20];
        wallDown = GamePlay.wallDown;
        wallRights = new Point[20];
        wallRight = GamePlay.wallRight;
        backgroundTile = Background.backgroundTile;
    }

    private void levelSetting() {
        MAP_WIDTH = input.nextInt();
        MAP_HEIGHT = input.nextInt();
        MAP_TOP = input.nextInt();
        MAP_LEFT = input.nextInt();
        GamePlay.MAP_WIDTH = MAP_WIDTH;
        GamePlay.MAP_HEIGHT = MAP_HEIGHT;
    }

    private void createMapWall() {
        int x,y;
        for (int i=1;i<=MAP_WIDTH;i++) {
            x = i + MAP_LEFT - 1;
            y = 0 + MAP_TOP - 1;
            WallControllerManager.instance.add(WallController.create(x,y,WallType.DOWN));
            y = MAP_HEIGHT  + MAP_TOP - 1;
            WallControllerManager.instance.add(WallController.create(x,y,WallType.DOWN));
        }

        for (int j=1;j<=MAP_HEIGHT;j++) {
            x = 0 + MAP_LEFT - 1;
            y = j + MAP_TOP - 1;
            WallControllerManager.instance.add(WallController.create(x,y,WallType.RIGHT));
            x = MAP_WIDTH + MAP_LEFT - 1;
            WallControllerManager.instance.add(WallController.create(x,y,WallType.RIGHT));
        }
    }

    private void createWallDown() {
        int n, column, row;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            column = input.nextInt();
            row = input.nextInt();
            wallDowns[i] = new Point(column,row);
        }

        for (int i=1;i<=n;i++) {
            int x = wallDowns[i].x + MAP_LEFT - 1;
            int y = wallDowns[i].y + MAP_TOP - 1;
            wallDown[x][y] = true;
            WallControllerManager.instance.add(WallController.create(x,y, WallType.DOWN));
        }
    }

    private void createWallRight() {
        int n, column, row;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            column = input.nextInt();
            row = input.nextInt();
            wallRights[i] = new Point(column,row);
        }

        for (int i=1;i<=n;i++) {
            int x = wallRights[i].x + MAP_LEFT - 1;
            int y = wallRights[i].y + MAP_TOP - 1;
            wallRight[x][y] = true;
            WallControllerManager.instance.add(WallController.create(x,y, WallType.RIGHT));
        }
    }

    private void createMummy() {
        int n, x, y;
        String type;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            x = input.nextInt();
            y = input.nextInt();
            type = input.next();
            if (MummyType.valueOf(type)==MummyType.WHITE)
                mummyControllers[i] = EnemyControllerWhite.create(x,y);
            if (MummyType.valueOf(type)==MummyType.RED) {}
        }

        for (int i=1;i<=n;i++) {
            EnemyController mummy = mummyControllers[i];
            mummy.setColumn(mummy.getColumn()+MAP_LEFT-1);
            mummy.setRow(mummy.getRow()+MAP_TOP-1);
            EnemyControllerManager.instance.add(mummy);
        }
    }

    private void createPlayer() {
        int x,y;
        x = input.nextInt();
        y = input.nextInt();
        PlayerController.instance.setColumn(x);
        PlayerController.instance.setRow(y);
        exitX = input.nextInt();
        exitY = input.nextInt();
    }

    public void createLevel(int level) {
        try {
            this.input = new Scanner(new File("script/" + level + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        levelSetting();
        createMapWall();
        createWallDown();
        createWallRight();
        createMummy();
        createPlayer();

        Background.instance.init();
        backgroundTile[exitX][exitY] = Utils.getImage("power-up.png");
        GamePlay.exitX = exitX + MAP_LEFT - 1;
        GamePlay.exitY = exitY + MAP_TOP - 1;
    }



    public boolean hasLose() {
        Vector<SingleController> singleControllers = EnemyControllerManager.instance.getManager();
        for (SingleController singleController : singleControllers) {
            GameObject go = singleController.getGameObject();
            if (go.getX()== PlayerController.instance.getX() &&
                    go.getY()==PlayerController.instance.getY()) return true;
        }
        return false;
    }

    public boolean hasWon() {
        if (PlayerController.instance.getColumn() == GamePlay.exitX &&
                PlayerController.instance.getRow() == GamePlay.exitY) return true;
        return false;
    }

    public void draw() {

    }

    void run() {
        
    }

    public static final GameLevel instance = new GameLevel();
}
